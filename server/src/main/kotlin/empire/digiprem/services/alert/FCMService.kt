package empire.digiprem.services.alert

import com.google.firebase.messaging.*
import com.google.gson.GsonBuilder
import empire.digiprem.dto.NotificationRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.concurrent.ExecutionException

@Service
class FCMService {
    private val logger: Logger = LoggerFactory.getLogger(javaClass.name)


    /*
    * La méthode sendMessageToToken() est notre principale méthode d'envoi de notification. Elle utilise les méthodes d'assistance getPreconfiguredMessageToToken() pour créer l'objet Message et sendAndGetResponse() pour envoyer le message au service FCM pour distribution.
     * */
    @Throws(InterruptedException::class, ExecutionException::class)
    fun sendMessageToken(request: NotificationRequest) {
        val message = getPreconfiguredMessageToToken(request).build()
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonOutput = gson.toJson(message)
        val response = sendAndGetResponse(message)
        logger.info("Sent message to token. Device token:" + request.token + ", " + response + " msg " + jsonOutput)
    }


    @Throws(InterruptedException::class, ExecutionException::class)
    private fun sendAndGetResponse(message: Message): String {
        return FirebaseMessaging.getInstance().sendAsync(message).get()
    }

    private fun getAndroidConfig(topic: String): AndroidConfig {
        return AndroidConfig.builder()
            .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
            .setPriority(AndroidConfig.Priority.HIGH)
            .setNotification(AndroidNotification.builder().setTag(topic).build())
            .build()
    }

    private fun getApnsConfig(topic: String): ApnsConfig {
        return ApnsConfig.builder().setAps(Aps.builder().setCategory(topic).build()).build()
    }

    private fun getPreconfiguredMessageToToken(request: NotificationRequest): Message.Builder {
        val androidConfig = getAndroidConfig(request.topic)
        val apnsConfig = getApnsConfig(request.topic)
        val notification = Notification.builder().setTitle(request.titre).setBody(request.body).build()

        return Message.builder().setToken(request.token).setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
            .setNotification(notification)
    }
}