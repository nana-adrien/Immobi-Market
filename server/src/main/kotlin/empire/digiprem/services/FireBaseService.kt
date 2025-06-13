package empire.digiprem.services

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.StorageClient
import org.springframework.stereotype.Service
import java.io.FileInputStream
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@Service
class AppFireBaseService {

    init {
        val serviceAccount = AppFireBaseService::class.java.classLoader.getResourceAsStream("java-firebase-sdk-firebase-adminsdk.json")
        ///FileInputStream("path/to/serviceAccountKey.json")
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setStorageBucket("gestion-evenementiel.appspot.com")
            .build()
//      FirebaseApp.getInstance()  //  initializeApp(options)
    }

    @OptIn(ExperimentalUuidApi::class)
    fun uploadImage(imageBytes: ByteArray, fileName: String):String {
        val bucket=StorageClient.getInstance().bucket();
        val objectName="profile_image${Uuid.random()}-$fileName"
        val blob=bucket.create(objectName,imageBytes,"image/jpeg")
        return String.format("https://storage.googleapis.com/%s/%s",blob.name)
    }
}