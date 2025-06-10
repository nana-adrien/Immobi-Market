package empire.digiprem.services

import empire.digiprem.models.NotificationCanal
import empire.digiprem.service.INotificationService
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val smsNotificationService: SmsNotificationService,
    private val emailNotificationService: EmailNotificationService
) :
    INotificationService {
    override fun sendNotification(to: String, subject: String, body: String, canal: NotificationCanal) {
        if (canal == NotificationCanal.EMAIL) {
            emailNotificationService.sendEmail(to, subject, body)
        } else {
            smsNotificationService.sendMessage(to, "$subject:\n $body")
        }
    }
}