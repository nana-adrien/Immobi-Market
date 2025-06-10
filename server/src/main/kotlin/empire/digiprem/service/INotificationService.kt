package empire.digiprem.service

import empire.digiprem.models.NotificationCanal

interface INotificationService {
    fun sendNotification(to: String, subject: String, body: String, canal: NotificationCanal)
}
