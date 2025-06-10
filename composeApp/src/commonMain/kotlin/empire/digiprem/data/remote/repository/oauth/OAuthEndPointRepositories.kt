package empire.digiprem.data.remote.repository.oauth

import empire.digiprem.data.remote.config.KtorfitServiceCreator
import empire.digiprem.data.remote.dto.oauth.RegisterBody
import empire.digiprem.domain.repository.remote.IOAuthEndPointRepository
import empire.digiprem.domain.repository.remote.IRegisterEndpoint
import empire.digiprem.dto.auth.AuthRespDto
import empire.digiprem.dto.auth.VerifyReqDto
import empire.digiprem.dto.auth.login.LoginRequestDTO2
import empire.digiprem.dto.auth.login.LoginResponseDTO2
import empire.digiprem.dto.auth.register.RegisterRequestDTO
import empire.digiprem.dto.auth.register.RegisterRequestDTO2
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityRequestDto
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityResponseDTO
import empire.digiprem.model.ApiResponse2
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OAuthEndPointRepositories: IRegisterEndpoint,KoinComponent{
    val ktrofit:KtorfitServiceCreator by inject()

    override suspend fun register(body: RegisterRequestDTO2): ApiResponse2<RegisterResponseDTO> {
        return  ktrofit.apiClient().create<IRegisterEndpoint>().register(body)    }

    override suspend fun verifyIdentity(body: VerifyIdentityRequestDto): ApiResponse2<VerifyIdentityResponseDTO> {
        return ktrofit.apiClient().create<IRegisterEndpoint>().verifyIdentity(body)
    }

    override suspend fun login(body: LoginRequestDTO2): ApiResponse2<LoginResponseDTO2> {
        return ktrofit.apiClient().create<IRegisterEndpoint>().login(body)  }
}