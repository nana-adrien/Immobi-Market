package empire.digiprem.services

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingException
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class FCMService2 {
    @Throws(FirebaseMessagingException::class)
    fun sendNotification(token: String?, title: String?, body: String?) {
        val notification = Notification.builder().setTitle(title).setBody(body).build()
        val message = Message.builder()
            .setToken(token)
            .setNotification(notification)
            .build()

        val fcmMessage = Message.builder()
            .putData("title", title)
            .putData("body", body) //.putData("message", message)
            .setToken(token)
            .build()

        try {
            FirebaseMessaging.getInstance().send(fcmMessage)
            println("Notification envoyée !")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        // FirebaseMessaging.getInstance().send(message);
    }
}