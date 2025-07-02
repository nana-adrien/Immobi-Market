package empire.digiprem.services.auth

import com.google.i18n.phonenumbers.NumberParseException
import empire.digiprem.dto.account.CreateAccountRequestDTO
import empire.digiprem.enums.FileUriTypeEnum
import empire.digiprem.enums.NotificationCanal
import empire.digiprem.enums.Role
import empire.digiprem.enums.VerificationOperation
import empire.digiprem.exception.ValidateTextFiledException
import empire.digiprem.model.AppFile
import empire.digiprem.models.database.*
import empire.digiprem.repositories.database.UserSettingsRepository
import empire.digiprem.repositories.database.UsersRepository
import empire.digiprem.repositories.database.UserAddressRepository
import empire.digiprem.repositories.database.IUserProfileRepository
import empire.digiprem.service.INotificationService
import empire.digiprem.service.IUserAccountService
import empire.digiprem.services.account.PhoneNumberService
import empire.digiprem.services.alert.EmailService
import empire.digiprem.services.file.FilesService
import empire.digiprem.utils.tools
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi

@Transactional
@Service
class UserAccountService(
    private val userAwaitingVerificationService: UserAwaitingVerificationService,
    private val notificationService: INotificationService,
    private val passwordEncoder: PasswordEncoder,
    private val userAddressRepository: UserAddressRepository,
    private val userProfileRepository: IUserProfileRepository,
    private val userSettingsRepository: UserSettingsRepository,
    private val filesService: FilesService,
    private val emailService: EmailService,
    private val passwordService: PasswordService,
    private val phoneNumberService: PhoneNumberService,
    private val usersRepository: UsersRepository
) : IUserAccountService {

    @OptIn(ExperimentalUuidApi::class)
    override fun createUserAccount(email: String, password: String): User {
        if (loadUserByIdentity(email, true) != null) {
            throw RuntimeException("User already exists")
        }
        val user = User(
            email = email,
            motDePasse = passwordEncoder.encode(password),
            role = Role.DEMANDEUR
        )
        return usersRepository.save(user).also {
            userSettingsRepository.save(
                UserSettings(
                    user = user,
                    enabledTwoFactorAuthentication = false
                )
            )
            val profile = UserProfile(
                user_id=user.id,
                user = user,
                nom = "IMU@${Random.nextInt(100000000)}",
            )
            userProfileRepository.save(
                profile.copy(
                    urlProfil = getUserAvatarUrl(
                        it.id.toString(),
                        user.email,
                    )
                ),
            )
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun getUserAvatarUrl(userId: String, name: String): String {
        val appFile = AppFile(
            byteArray = tools.createAvatarImage(name),//file.bytes,
            mimeType = FileUriTypeEnum.IMAGE_JPEG,
            mimeTypeDescriptor = "image/jpeg",
            name = userId,
        )
        return filesService.uploadProfile(userId, appFile)
    }
    fun createAccount(email: String, requestDTO: CreateAccountRequestDTO, profilUrl: String): UserProfile {
        val user = loadUserByIdentity(email, true) ?:
        throw ValidateTextFiledException(
            "identity",
            "User not found with email: $email"
        )

        userSettingsRepository.save(
            userSettingsRepository.findByUser(user).get().copy(
                enabledTwoFactorAuthentication = requestDTO.enabledTwoFactorAuth
            )
        )

        println("val user = $user")
        val profile = userProfileRepository.findByUser(user).get().copy(
            nom = requestDTO.lastName,
            prenom = requestDTO.firstName,
        ).also { profile ->
            profile.copy(
                telephoneUtilisateur = requestDTO.contact?.let {
                    createPhoneNumber(
                        UserPhoneNumber(
                            id =  profile.user_id,
                            userProfile = profile,
                            countryCode = it.countryCode!!,
                            phoneNumber = it.phoneNumber
                        )
                    )
                }
            )
        }
        return userProfileRepository.save(profile)
    }

    override fun createUserAccount(user: User): User {
        var savedUser: User? = usersRepository.findByEmail(user.email).orElse(null)
        if (savedUser != null) {
            throw RuntimeException("User already  exists ")
        }

        savedUser = usersRepository.save(user)
        userSettingsRepository.save(
            UserSettings(
                id = savedUser.id,
                user = savedUser,
                enabledTwoFactorAuthentication = false
            )
        )
        userProfileRepository.save(
            UserProfile(
                user_id = savedUser.id,
                user = savedUser,
                nom = "IMU@${Random.nextInt(100000000)}",
                urlProfil = getUserAvatarUrl(
                    savedUser.id.toString(),
                    savedUser.email,
                )
            ),
        )

        return user
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

    fun forgotPassword(identity: String, isEmail: Boolean): User? {
        val users = loadUserByIdentity(identity, isEmail)
        val isSend =
            sendVerificationCode(identity, null, isEmail, "Forgot Password", VerificationOperation.AUTHENTICATION)
        return if (isSend) users else null
    }

    @OptIn(ExperimentalUuidApi::class)
    fun resetPassword(identity: String, isEmail: Boolean, resetCode: String, newPassword: String?): Boolean {
        val userAwaitingVerification = verifyCode(identity, resetCode, VerificationOperation.NOT_VERIFY)
        var user = loadUserByIdentity(userAwaitingVerification!!.identifier, userAwaitingVerification.isEmail)
            ?: throw ValidateTextFiledException("identity", "User not found with identifier: $identity")

        user = user.copy(motDePasse = passwordEncoder.encode(newPassword))
        val message = String.format(
            "Bonjour %s,\n\nVotre mot de passe a bien été réinitialisé. Si vous n'êtes pas à l'origine de cette action, veuillez contacter le support immédiatement.",
            """
                ${userProfileRepository.getReferenceById(user.id).nom}Cordialement,
               Immobi-Market.
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

    override fun loadUserByIdentity(identity: String, isEmail: Boolean): User? {
        val user: Optional<User> = usersRepository.findByEmail(identity)
        return user.orElse(null)
    }

    override fun loadUserByIdentityEx(identity: String, countryCode: String, isEmail: Boolean): User? {


        val user: Optional<User> = usersRepository.findByEmail(identity)

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