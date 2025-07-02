package empire.digiprem.services.auth

import empire.digiprem.enums.NotificationCanal
import empire.digiprem.enums.Role
import empire.digiprem.enums.VerificationOperation
import empire.digiprem.models.database.User
import empire.digiprem.models.database.UserAwaitingVerification
import empire.digiprem.repositories.database.UserAwaitingVerificationRepository
import empire.digiprem.service.INotificationService
import empire.digiprem.service.IUserAccountService
import empire.digiprem.service.IUserDetailService2
import jakarta.transaction.Transactional
import jakarta.validation.constraints.NotBlank
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Transactional
@Service
class UserAwaitingVerificationService(
    private val userAwaitingVerificationRepository: UserAwaitingVerificationRepository,
    private val notificationService: INotificationService,
    private val passwordEncoder: PasswordEncoder
) {
    private val userDetailService: IUserDetailService2? = null
    private val userAccountService: IUserAccountService? = null

    fun generateVerificationCode(): String {
        return String.format("%06d", Random().nextInt(999999))
    }

    fun generateResetCode(): String {
        val chars = "ABCDEFGHIJKLMOPQRSTUVWXYZ0123456789"
        val code = StringBuilder()
        val random: Random = SecureRandom()

        for (i in 0..5) {
            code.append(chars[random.nextInt(chars.length)])
        }
        return code.toString()
    }

    fun sendVerificationCode(
        operation: VerificationOperation,
        identifier: String,
        password: String,
        isEmail: Boolean,
        subject: String
    ): UserAwaitingVerification {
        val isSendCode = false
        val canal = if (isEmail) NotificationCanal.EMAIL else NotificationCanal.SMS
        val code = sendVerificationCode(identifier, subject, canal)
        val userAwaitingVerification = UserAwaitingVerification(
            identifier = identifier,
            password = password,
            isEmail = isEmail,
            generatedAt = LocalDateTime.now(),
            expiresAt = LocalDateTime.now().plusMinutes(5),
            subject = subject,
            operation = operation,
            attempts = 1,
            code = code,
        )
        return userAwaitingVerificationRepository.save(userAwaitingVerification)
    }

    fun resendVerificationCode(userAwaitingVerification: UserAwaitingVerification): Boolean {
        var isSendCode = false
        val AWAITING_SEND_VERIFICATIONS_COUNT = 5
        if (userAwaitingVerification.attempts >= AWAITING_SEND_VERIFICATIONS_COUNT) {
            throw RuntimeException("Tentatives de vérification de code dépassées")
        }
        val canal = if (userAwaitingVerification.isEmail) NotificationCanal.EMAIL else NotificationCanal.SMS
        val code = sendVerificationCode(userAwaitingVerification.identifier, userAwaitingVerification.subject, canal)

        userAwaitingVerification.code = code
        userAwaitingVerification.attempts = userAwaitingVerification.attempts + 1
        userAwaitingVerification.generatedAt = LocalDateTime.now()
        userAwaitingVerification.expiresAt = LocalDateTime.now().plusMinutes(5)
        userAwaitingVerificationRepository.save(userAwaitingVerification)
        isSendCode = true
        return isSendCode
    }

    fun sendVerificationCode(to: String, subject: String, canal: NotificationCanal): String {
        val code = generateVerificationCode()
        notificationService.sendNotification(to, subject, "Your verification code is: $code", canal)
        return code
    }

    fun verifyCode(inputCode: String, actualCode: String): Boolean {
        return inputCode == actualCode
    }

    fun getUserAwaitingVerificationByIdentifier(identifier: String): UserAwaitingVerification? {
        return userAwaitingVerificationRepository.getUserAwaitingVerificationByIdentifier(identifier).orElse(null)
    }

    fun getUserAwaitingVerificationByCode(code: String): UserAwaitingVerification {
        return userAwaitingVerificationRepository.getUserAwaitingVerificationByCode(code)
    }

    fun saveUserAwaitingVerification(userAwaitingVerification: UserAwaitingVerification): UserAwaitingVerification {
        return userAwaitingVerificationRepository.save(userAwaitingVerification)
    }

    fun deleteUserAwaitingVerification(userAwaitingVerification: UserAwaitingVerification) {
        userAwaitingVerificationRepository.delete(userAwaitingVerification)
    }

    fun createAwaitingUser(
        email: String = "",
        password: @NotBlank String = "",
        operation: VerificationOperation = VerificationOperation.AUTHENTICATION
    ): UserAwaitingVerification {
        val userAwaitingVerification = UserAwaitingVerification(
            identifier = email,
            password = password,
            isEmail = true,
            generatedAt = LocalDateTime.now(),
            expiresAt = LocalDateTime.now().plusMinutes(15),
            operation = operation,
        )
        return userAwaitingVerification
    }

    fun createAwaitingUser(
        phoneNumber: String,
        countryCode: String,
        password: @NotBlank String,
        operation: VerificationOperation
    ): UserAwaitingVerification {
        val userAwaitingVerification = UserAwaitingVerification(
            identifier = countryCode.trim { it <= ' ' } + phoneNumber.trim { it <= ' ' },
            password = password,
            countryCode = countryCode,
            isEmail = false,
            generatedAt = LocalDateTime.now(),
            expiresAt = LocalDateTime.now().plusMinutes(15),
            operation = operation,
        )
        return userAwaitingVerification
    }

    fun addToAwaitingFile(userAwaitingVerification: UserAwaitingVerification): UserAwaitingVerification {
        return userAwaitingVerificationRepository.save(userAwaitingVerification)
    }

    fun loadUserAwaitingVerificationByIdentity(identity: String): UserAwaitingVerification? {
        return userAwaitingVerificationRepository.getUserAwaitingVerificationByIdentifier(identity).orElse(null)
    }

    fun getIdentifyUser(identity: String): User {
        val userAwaitingVerification = userAwaitingVerificationRepository.getUserAwaitingVerificationByIdentifier(identity).getOrNull()
        if (userAwaitingVerification?.operation == VerificationOperation.REGISTRATION) {
            val user = createUser(userAwaitingVerification)
            deleteUserAwaitingVerification(userAwaitingVerification)
            return user
        } else {
            println("userAwaitingVerification.getIsEmail()=" + userAwaitingVerification?.isEmail + "" + userAwaitingVerification.toString())
            val newIdentity =
                if (userAwaitingVerification?.isEmail == true) userAwaitingVerification?.identifier ?: ""
                else userAwaitingVerification?.identifier?.substring(userAwaitingVerification.countryCode.length) ?: ""
            val user = userAwaitingVerification?.let {
                userAccountService!!.loadUserByIdentityEx(
                    newIdentity,
                    it.countryCode,
                    it.isEmail
                )
            } as User
            deleteUserAwaitingVerification(userAwaitingVerification)
            return user
        }
    }

    @OptIn(kotlin.uuid.ExperimentalUuidApi::class)
    private fun createUser(userAwaitingVerification: UserAwaitingVerification): User {
        val user = User(
            email = if (userAwaitingVerification.isEmail) userAwaitingVerification.identifier else "",
            motDePasse = passwordEncoder.encode(userAwaitingVerification.password),
            role = Role.DEMANDEUR
        )
        return user
    }
}