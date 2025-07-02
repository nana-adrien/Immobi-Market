package empire.digiprem.data.remote.repository.oauth

import empire.digiprem.data.remote.config.KtorfitServiceCreator
import empire.digiprem.domain.repository.remote.IOAuthEndPointRepository
import empire.digiprem.domain.repository.remote.createIOAuthEndPointRepository
import empire.digiprem.dto.auth.login.LoginRequestDTO2
import empire.digiprem.dto.auth.login.LoginResponseDTO2
import empire.digiprem.dto.auth.refresh_token.RefreshTokenReqDTO
import empire.digiprem.dto.auth.refresh_token.RefreshTokenResponseDTO
import empire.digiprem.dto.auth.register.RegisterRequestDTO2
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityRequestDto
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityResponseDTO
import empire.digiprem.model.ApiResponse2
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OAuthEndPointRepositories: IOAuthEndPointRepository,KoinComponent{
    val ktrofit:KtorfitServiceCreator by inject()

    override suspend fun register(body: RegisterRequestDTO2): ApiResponse2<RegisterResponseDTO> {
        return  ktrofit.apiClient().create<IOAuthEndPointRepository>().register(body)    }

    override suspend fun verifyIdentity(body: VerifyIdentityRequestDto): ApiResponse2<VerifyIdentityResponseDTO> {
        return ktrofit.apiClient().create<IOAuthEndPointRepository>().verifyIdentity(body)
    }

    override suspend fun login(body: LoginRequestDTO2): ApiResponse2<LoginResponseDTO2> {
        return ktrofit.apiClient().create<IOAuthEndPointRepository>().login(body)  }

    override suspend fun refreshToken(body: RefreshTokenReqDTO): ApiResponse2<RefreshTokenResponseDTO> {
       return ktrofit.apiClient().createIOAuthEndPointRepository().refreshToken(body)
    }
}