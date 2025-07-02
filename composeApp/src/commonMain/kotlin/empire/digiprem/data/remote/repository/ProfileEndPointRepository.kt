package empire.digiprem.data.remote.repository

import empire.digiprem.data.remote.config.KtorfitServiceCreator
import empire.digiprem.domain.repository.remote.IProfileEndPoint
import empire.digiprem.dto.auth.register.RegisterRequestDTO2
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.dto.profile.GetProfileResponse
import empire.digiprem.dto.profile.UpdateProfileRequest
import empire.digiprem.dto.profile.UpdateProfileResponse
import empire.digiprem.model.ApiResponse2
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileEndPointRepository:IProfileEndPoint , KoinComponent{
    private val ktorfitService:KtorfitServiceCreator by inject()
    override suspend fun getProfile(): ApiResponse2<GetProfileResponse> {
     return  ktorfitService.apiClient().create<IProfileEndPoint>().getProfile()
    }

    override suspend fun updateProfile(body: UpdateProfileRequest): ApiResponse2<UpdateProfileResponse> {
        return  ktorfitService.apiClient().create<IProfileEndPoint>().updateProfile(body)
    }

}