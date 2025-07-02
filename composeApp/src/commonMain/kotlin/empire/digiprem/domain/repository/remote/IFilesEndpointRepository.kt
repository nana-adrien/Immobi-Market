package empire.digiprem.domain.repository.remote



import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.Multipart
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Part
import empire.digiprem.dto.image.UploadProfileResponseDTO
import empire.digiprem.dto.profile.UpdateProfileResponse
import empire.digiprem.model.ApiResponse2
import io.ktor.client.request.forms.*
import io.ktor.http.content.*

interface IFilesEndpointRepository {
    @Multipart
    @POST("v2/files/upload")
    suspend fun uploadDocument(
        @Part("title") title: String,
        @Part("description") description: String,
        @Part("file") file: PartData,
    ):ApiResponse2<String>
    @Multipart
    @POST("v2/files/upload-profile-image")
    suspend fun uploadProfileImage(
        @Body file: MultiPartFormDataContent,
    ):ApiResponse2<UploadProfileResponseDTO>
}