package empire.digiprem.services

import empire.digiprem.models.NotificationCanal
import empire.digiprem.service.INotificationService
import org.springframework.stereotype.Service
import java.util.*

@Service
class TwoFactorAuthenticationService(private val notificationService: INotificationService) {
    fun generateVerificationCode(): String {
        return String.format("%06d", Random().nextInt(999999))
    }

    fun sendVerificationCode(to: String, subject: String, canal: NotificationCanal): String {
        val code = generateVerificationCode()
        notificationService.sendNotification(to, subject, "Your verification code is: $code", canal)
        return code
    }

    fun verifyCode(inputCode: String, actualCode: String): Boolean {
        return inputCode == actualCode
    }
}