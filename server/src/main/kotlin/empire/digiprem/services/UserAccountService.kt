package empire.digiprem.services

import com.google.i18n.phonenumbers.NumberParseException
import empire.digiprem.dto.account.CreateAccountRequestDTO
import empire.digiprem.exception.ValidateTextFiledException
import empire.digiprem.models.*
import empire.digiprem.repositories.UserSettingsRepository
import empire.digiprem.repositories.UsersRepository
import empire.digiprem.repositories.account.UserAddressRepository
import empire.digiprem.service.INotificationService
import empire.digiprem.service.IUserAccountService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserAccountService(
    private val userAwaitingVerificationService: UserAwaitingVerificationService,
    private val notificationService: INotificationService,
    private val passwordEncoder: PasswordEncoder,
    private val userAddressRepository: UserAddressRepository,
    private val userSettingsRepository: UserSettingsRepository,
    private val emailService: EmailService,
    private val passwordService: PasswordService,
    private val phoneNumberService: PhoneNumberService,
    usersRepository: UsersRepository
) : IUserAccountService {
    private val usersRepository: UsersRepository = usersRepository

    override fun createUserAccount(email: String, password: String): Users {
        if (loadUserByIdentity(email, true) != null) {
            throw RuntimeException("User already exists")
        }
        val user = Users(
            email = email,
            userPassword = passwordEncoder.encode(password)
        )
        return usersRepository.save(user)
    }

    fun createAccount(email: String, requestDTO: CreateAccountRequestDTO):Users {
        var user = loadUserByIdentity(email, true) ?: throw ValidateTextFiledException("identity","User not found with email: $email")
        val userAddress = userAddressRepository.findByContinentAndCountryAndCityAndRegionAndDistrict(
            "Afrique",
            "Cameroun",
            requestDTO.city,
            requestDTO.region,
            requestDTO.district
        ).orElse(
            UserAddress(
                continent = "Afrique",
                country = "Cameroun",
                region = requestDTO.region,
                city = requestDTO.city,
                district = requestDTO.district,
            )
        )
        val userSettings = userSettingsRepository.save(
            UserSettings(enabledTwoFactorAuthentication = requestDTO.enabledTwoFactorAuth)
        )
        val phoneNumber = createPhoneNumber(UserPhoneNumber(countryCode = "+237", phoneNumber = requestDTO.phone))

        user = user.copy(
            name = requestDTO.lastName,
            firstName = requestDTO.firstName,
            profileUrl = requestDTO.profileUrl?:"",
            userAddress = userAddress,
            settings = userSettings,
            isEmailVerified = true,
            userPhoneNumber = phoneNumber,
        )
     return   usersRepository.save(user)
    }

    override fun createUserAccount(user: Users): Users {
        val savedUser: Users? =
            usersRepository.findByPhoneNumber(user.phoneNumber).or { usersRepository.findByEmail(user.email) }
                .orElse(null)
        if (savedUser != null) {
            throw RuntimeException("User already exists")
        }
        return usersRepository.save(user)
    }

    override fun validateEmail(email: String): Boolean {
        if (emailService.validateEmail(email)) {
            return true
        }
        throw RuntimeException("adresse email invalide ")
    }

    override fun validateEmail2(email: String): String {
        if (emailService.validateEmail(email)) {
            return email
        }
        throw RuntimeException("adresse email invalide ")
    }

    override fun validatePassword(password: String): Boolean {
        return passwordService.validatePassword(password)
    }

    override fun generateVerificationCode(): String {
        return userAwaitingVerificationService.generateVerificationCode()
    }

    override fun generateResetCode(): String {
        return userAwaitingVerificationService.generateResetCode()
    }

    override fun verifyCode(inputCode: String, actualCode: String): Boolean {
        return userAwaitingVerificationService.verifyCode(inputCode, actualCode)
    }

    override fun validatePhoneNumber(phoneNumber: String, countryCode: String): Boolean {
        try {
            if (phoneNumberService.validatePhoneNumber(countryCode, phoneNumber, null)) {
                return true
            }
            throw RuntimeException(" phone number invalide ")
        } catch (e: NumberParseException) {
            throw RuntimeException(" phone number invalide ")
        }
    }

    override fun validatePhoneNumber2(phoneNumber: String, countryCode: String): String {
        try {
            if (phoneNumberService.validatePhoneNumber(countryCode, phoneNumber, null)) {
                return countryCode.trim { it <= ' ' } + phoneNumber.trim { it <= ' ' }
            }
            throw RuntimeException(" phone number invalide ")
        } catch (e: NumberParseException) {
            throw RuntimeException(" phone number invalide ")
        }
    }

    override fun sendVerificationCode(
        identity: String,
        password: String?,
        isEmail: Boolean,
        subject: String,
        operation: VerificationOperation
    ): Boolean {
        val userAwaitingVerification = userAwaitingVerificationService.getUserAwaitingVerificationByIdentifier(identity)
        if (userAwaitingVerification != null) {
            throw RuntimeException(
                """A verification code has already been sent to the address ${userAwaitingVerification.identifier}
 It expires on :${userAwaitingVerification.expiresAt}
 You still have the possibility to renew it"""
            )
        }
        return userAwaitingVerificationService.sendVerificationCode(
            operation,
            identity,
            password ?: "",
            isEmail,
            subject
        ) != null
    }

    override fun sendVerifyIdentityPinCode(
        identity: String,
        password: String,
        isEmail: Boolean,
        subject: String,
        operation: VerificationOperation
    ): UserAwaitingVerification {
        val userAwaitingVerification = userAwaitingVerificationService.getUserAwaitingVerificationByIdentifier(identity)
        if (userAwaitingVerification != null) {
            throw RuntimeException(
                """A verification code has already been sent to the address ${userAwaitingVerification.identifier}
 It expires on :${userAwaitingVerification.expiresAt}
 You still have the possibility to renew it"""
            )
        }
        return userAwaitingVerificationService.sendVerificationCode(operation, identity, password, isEmail, subject)
    }

    override fun resendVerificationCode(identity: String): Boolean {
        val userAwaitingVerification = userAwaitingVerificationService.getUserAwaitingVerificationByIdentifier(identity)
            ?: throw RuntimeException("Verification code not found or already expired")
        return userAwaitingVerificationService.resendVerificationCode(userAwaitingVerification)
    }

    override fun deleteUserAwaitingVerification(userAwaitingVerification: UserAwaitingVerification) {
        userAwaitingVerificationService.deleteUserAwaitingVerification(userAwaitingVerification)
    }

    override fun verifyCode(
        identity: String,
        verificationCode: String,
        operation: VerificationOperation
    ): UserAwaitingVerification {
        return verifyPinCode(identity, verificationCode)
    }

    override fun verifyPinCode(identity: String, verificationCode: String): UserAwaitingVerification {
        val userAwaitingVerification = userAwaitingVerificationService.getUserAwaitingVerificationByIdentifier(identity)
            ?: throw RuntimeException("Verification code not found or already expired")
        if (!userAwaitingVerificationService.verifyCode(verificationCode, userAwaitingVerification.code)) {
            throw RuntimeException("Invalid verification code")
        }
        /*if (userAwaitingVerification.getOperation()==VerificationOperation.NOT_VERIFY){
            userAwaitingVerification.setCode(userAwaitingVerificationService.generateResetCode());
            userAwaitingVerification.setGeneratedAt(LocalDateTime.now());
            userAwaitingVerification.setExpiresAt(LocalDateTime.now().plusMinutes(5));
            userAwaitingVerification.setOperation(VerificationOperation.VERIFY);
            userAwaitingVerificationService.saveUserAwaitingVerification(userAwaitingVerification);
        }*/
        return userAwaitingVerification
    }

    fun forgotPassword(identity: String, isEmail: Boolean): Users? {
        val users = loadUserByIdentity(identity, isEmail)
        val isSend =
            sendVerificationCode(identity, null, isEmail, "Forgot Password", VerificationOperation.AUTHENTICATION)
        return if (isSend) users else null
    }

    fun resetPassword(identity: String, isEmail: Boolean, resetCode: String, newPassword: String?): Boolean {
        val userAwaitingVerification = verifyCode(identity, resetCode, VerificationOperation.NOT_VERIFY)
        val user = loadUserByIdentity(userAwaitingVerification!!.identifier, userAwaitingVerification.isEmail)
            ?: throw ValidateTextFiledException("identity", "User not found with identifier: $identity")

        user.userPassword = passwordEncoder.encode(newPassword)
        usersRepository.save(user)
        val message = String.format(
            "Bonjour %s,\n\nVotre mot de passe a bien été réinitialisé. Si vous n'êtes pas à l'origine de cette action, veuillez contacter le support immédiatement.",
            """
                ${user?.name}Cordialement,
                L'équipe de sécurité.
                """.trimIndent()
        )
        notificationService.sendNotification(
            identity,
            "Réinitialisation du mot de passe",
            message,
            if (isEmail) NotificationCanal.EMAIL else NotificationCanal.SMS
        )
        userAwaitingVerificationService.getUserAwaitingVerificationByIdentifier(identity)?.let {
            userAwaitingVerificationService.deleteUserAwaitingVerification(it)
        }
        return true
    }

    override fun loadUserByIdentity(identity: String, isEmail: Boolean): Users? {
        val user: Optional<Users> =
            if (isEmail) usersRepository.findByEmail(identity) else usersRepository.findByPhoneNumber(identity)
        return user.orElse(null)
    }

    override fun loadUserByIdentityEx(identity: String, countryCode: String, isEmail: Boolean): Users? {

        val phoneNumber = phoneNumberService.loadUserPhoneNumberByCountryCodeAndPhoneNumber(
            countryCode,
            identity.substring(countryCode.length)
        ) ?: throw ValidateTextFiledException("phone", "Phone number $identity not found ")
        val user: Optional<Users> =
            if (isEmail) usersRepository.findByEmail(identity)
            else usersRepository.findByUserPhoneNumber(phoneNumber)
        return user.orElse(null)
    }

    override fun createPhoneNumber(userPhoneNumber: UserPhoneNumber): UserPhoneNumber {
        if (phoneNumberService.loadUserPhoneNumberByCountryCodeAndPhoneNumber(
                userPhoneNumber.countryCode,
                userPhoneNumber.phoneNumber
            ) != null
        ) {
            throw ValidateTextFiledException("phone", "Phone number already exists")
        }
        return phoneNumberService.savePhoneNumber(userPhoneNumber)
    }
}