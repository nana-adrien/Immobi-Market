package empire.digiprem.services

import empire.digiprem.models.CodeVerification
import empire.digiprem.models.NotificationCanal
import empire.digiprem.repositories.CodeVerificationRepository
import empire.digiprem.service.INotificationService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Transactional
@Service
class CodeVerificationService(
    codeVerificationRepository: CodeVerificationRepository,
    private val notificationService: INotificationService
) {
    private val codeVerificationRepository: CodeVerificationRepository = codeVerificationRepository

    fun generateVerificationCode(): String {
        return String.format("%06d", Random().nextInt(999999))
    }

    fun sendVerificationCode(codeVerification: CodeVerification, subject: String): String {
        var canal = NotificationCanal.EMAIL
        if (!codeVerification.isEmail) {
            canal = NotificationCanal.SMS
        }
        val code = sendVerificationCode(codeVerification.identifier, subject, canal)
        codeVerification.code = code
        val userInformationSaved: CodeVerification = codeVerificationRepository.save(codeVerification)
        return userInformationSaved.code
    }

    fun resendVerificationCode(codeVerification: CodeVerification, subject: String): String {
        if (codeVerification.attempts == 0) {
            throw RuntimeException("Tentatives de vérification de code dépassées")
        }
        var canal = NotificationCanal.EMAIL
        if (!codeVerification.isEmail) {
            canal = NotificationCanal.SMS
        }
        val code = sendVerificationCode(codeVerification.identifier, subject, canal)

        codeVerification.code = code
        codeVerification.attempts = codeVerification.attempts - 1
        codeVerification.generatedAt = LocalDateTime.now()
        codeVerification.expiresAt = LocalDateTime.now().plusMinutes(5)
        val userInformationSaved: CodeVerification = codeVerificationRepository.save(codeVerification)
        return userInformationSaved.code
    }

    fun sendVerificationCode(to: String, subject: String, canal: NotificationCanal): String {
        val code = generateVerificationCode()
        notificationService.sendNotification(to, subject, "Your verification code is: $code", canal)
        return code
    }

    fun verifyCode(inputCode: String, actualCode: String): Boolean {
        return inputCode == actualCode
    }

    fun getCodeVerification(identifier: String): CodeVerification {
        return codeVerificationRepository.getCodeVerificationsByIdentifier(identifier).orElseThrow {
            RuntimeException(
                "Identifant introuvable "
            )
        }
    }

    fun deleteCodeVerification(codeVerification: CodeVerification) {
        codeVerificationRepository.delete(codeVerification)
    }
}