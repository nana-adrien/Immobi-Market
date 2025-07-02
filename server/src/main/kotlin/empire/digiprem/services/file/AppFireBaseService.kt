package empire.digiprem.services.file

import com.google.firebase.cloud.StorageClient
import org.springframework.stereotype.Service
import java.net.URLEncoder
import java.util.*
import kotlin.uuid.ExperimentalUuidApi


@Service
class AppFireBaseService {

    @OptIn(ExperimentalUuidApi::class)
    fun uploadImage(imagePath:String, mimeType:String ,imageBytes: ByteArray):String {
        val bucket=StorageClient.getInstance().bucket();
      //  val objectName="profile_image${Uuid.random()}-$fileName"
        val blob=bucket.create(imagePath,imageBytes,mimeType)
       // return "https://storage.googleapis.com/${blob.bucket}/${blob.name}"

        val token = UUID.randomUUID().toString()
        blob.toBuilder().setMetadata(mapOf("firebaseStorageDownloadTokens" to token)).build().update()

        return "https://firebasestorage.googleapis.com/v0/b/${bucket.name}/o/${URLEncoder.encode(blob.name, "UTF-8")}?alt=media&token=$token"
    }



}