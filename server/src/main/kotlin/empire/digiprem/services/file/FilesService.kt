package empire.digiprem.services.file

import empire.digiprem.model.AppFile
import org.springframework.stereotype.Service
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Service
class FilesService(
    private val appFireBaseService: AppFireBaseService,
) {

    @OptIn(ExperimentalUuidApi::class)
    fun uploadProfile(userId: String, file: AppFile): String {
        val profilePah = "immobi_market/users/$userId/profile/immobi_market_image${Uuid.random()}"
        return appFireBaseService.uploadImage(profilePah, file.mimeTypeDescriptor, file.byteArray!!)
    }


    @OptIn(ExperimentalUuidApi::class)
    suspend fun uploadPropertyImages(userId: String, propertyId: String, imagesUris: List<AppFile>): List<String> {
        val propertyImagesPath = "immobi_market/users/$userId/properties/$propertyId/immobi_market_image${Uuid.random()}"
        val propertyImageUrl = mutableListOf<String>()
        imagesUris.forEachIndexed { index, file ->
            val imageUrl = appFireBaseService.uploadImage(propertyImagesPath, file.mimeTypeDescriptor, file.byteArray!!)
            propertyImageUrl.add(imageUrl)
        }
        return propertyImageUrl
    }

}