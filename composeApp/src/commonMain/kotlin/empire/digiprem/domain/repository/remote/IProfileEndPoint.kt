package empire.digiprem.domain.repository.remote

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import empire.digiprem.dto.auth.register.RegisterRequestDTO2
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.dto.profile.GetProfileResponse
import empire.digiprem.dto.profile.UpdateProfileRequest
import empire.digiprem.dto.profile.UpdateProfileResponse
import empire.digiprem.model.ApiResponse2

interface IProfileEndPoint {
    @GET("v2/profile/get")
    suspend fun getProfile(): ApiResponse2<GetProfileResponse>
    @PUT("v2/profile/get")
    suspend fun updateProfile(@Body body: UpdateProfileRequest): ApiResponse2<UpdateProfileResponse>

}