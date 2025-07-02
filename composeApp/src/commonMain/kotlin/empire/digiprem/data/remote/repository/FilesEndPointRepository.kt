package empire.digiprem.data.remote.repository

import empire.digiprem.data.remote.config.KtorfitServiceCreator
import empire.digiprem.domain.repository.remote.IFilesEndpointRepository
import empire.digiprem.dto.image.UploadProfileResponseDTO
import empire.digiprem.dto.profile.UpdateProfileResponse
import empire.digiprem.model.ApiResponse2
import io.ktor.client.request.forms.*
import io.ktor.http.content.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FilesEndPointRepository: IFilesEndpointRepository,KoinComponent {

    val ktorfitService:KtorfitServiceCreator by inject()

    override suspend fun uploadDocument(title: String, description: String, file: PartData): ApiResponse2<String> {
        return ktorfitService.apiClient().create<IFilesEndpointRepository>().uploadDocument(title,description,file)
    }

    override suspend fun uploadProfileImage(file: MultiPartFormDataContent): ApiResponse2<UploadProfileResponseDTO> {
        return ktorfitService.apiClient().create<IFilesEndpointRepository>().uploadProfileImage(file)
    }
}