package empire.digiprem.data.remote.repository.files

import empire.digiprem.data.remote.config.KtorfitServiceCreator
import empire.digiprem.domain.repository.remote.IFilesEndpointRepository
import empire.digiprem.dto.profile.UpdateProfileResponse
import empire.digiprem.model.ApiResponse2
import io.ktor.http.content.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
/*
class FilesEndpointRepository: IFilesEndpointRepository,KoinComponent {

    private val ktorfitService:KtorfitServiceCreator by inject()

    override suspend fun uploadDocument(title: String, description: String, file: PartData): ApiResponse2<String> {
        return  ktorfitService.apiClient().create<IFilesEndpointRepository>().uploadDocument(title, description, file)
    }

    override suspend fun uploadProfileImage(file: PartData): ApiResponse2<UpdateProfileResponse> {
        return  ktorfitService.apiClient().create<IFilesEndpointRepository>().uploadProfileImage(file)
    }
}*/
