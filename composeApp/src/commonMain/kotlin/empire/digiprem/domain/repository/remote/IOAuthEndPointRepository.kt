package empire.digiprem.domain.repository.remote

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import empire.digiprem.dto.auth.login.LoginRequestDTO2
import empire.digiprem.dto.auth.login.LoginResponseDTO2
import empire.digiprem.dto.auth.refresh_token.RefreshTokenReqDTO
import empire.digiprem.dto.auth.refresh_token.RefreshTokenResponseDTO
import empire.digiprem.dto.auth.register.RegisterRequestDTO2
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityRequestDto
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityResponseDTO
import empire.digiprem.model.ApiResponse2
interface IOAuthEndPointRepository {
    @POST("v2/auth/register")
    suspend fun register(@Body body: RegisterRequestDTO2):ApiResponse2<RegisterResponseDTO>
    @POST("v2/auth/verify-identity")
    suspend fun verifyIdentity(@Body body: VerifyIdentityRequestDto):ApiResponse2<VerifyIdentityResponseDTO>
    @POST("v2/auth/login")
    suspend fun login(@Body body: LoginRequestDTO2):ApiResponse2<LoginResponseDTO2>
    @POST("v2/auth/refresh-token")
    suspend fun refreshToken(@Body body: RefreshTokenReqDTO):ApiResponse2<RefreshTokenResponseDTO>
}