package empire.digiprem.services.alert

import com.google.api.client.util.Value
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException
import javax.annotation.PostConstruct

@Service
class FCMInitializer {
    @Value("\${app.firebase-configuration-file}") // Injection du chemin du fichier
    private val firebaseConfigPath: String? = null

    var logger: Logger = LoggerFactory.getLogger(FCMInitializer::class.java)

    /*
    * L' annotation @PostConstruct permet d'appeler la méthode initialize() au démarrage de l'application.
     * */
    @PostConstruct
    fun initialize() {
        try {
            println("📂 Chemin du fichier Firebase : $firebaseConfigPath")
            /*val inputStream =
                FCMInitializer::class.java.classLoader.getResourceAsStream("java-firebase-sdk-firebase-adminsdk.json")

            val option = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream *//*new ClassPathResource(firebaseConfigPath).getInputStream())*//*))
                .build()
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(option)
                logger.info("Firebase App initialized")
            }*/
        } catch (e: IOException) {
            logger.error(e.message)
            throw RuntimeException(e)
        }
    }
}