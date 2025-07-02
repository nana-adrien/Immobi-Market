package empire.digiprem.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class FirebaseConfig {
    @PostConstruct
    fun init() {
        val serviceAccount= FirebaseConfig::class.java.classLoader?.getResourceAsStream("firebase-admin-token.json")

        val option=FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setStorageBucket("gestion-evenementiel.appspot.com")
            .build()

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(option)
       }
    }
}