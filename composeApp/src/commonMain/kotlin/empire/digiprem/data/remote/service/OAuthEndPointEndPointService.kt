package empire.digiprem.data.remote.service

import empire.digiprem.data.config.AbstractDatasourceEventController
import empire.digiprem.data.config.DataSourceEvent
import empire.digiprem.data.remote.repository.oauth.OAuthEndPointRepositories
import empire.digiprem.domain.repository.remote.IRegisterEndpoint
import empire.digiprem.domain.servives.oauth.IOAuthEndPointService
import empire.digiprem.dto.auth.login.LoginRequestDTO2
import empire.digiprem.dto.auth.register.RegisterRequestDTO2
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityRequestDto
import kotlinx.coroutines.flow.Flow

class OAuthEndPointEndPointService:AbstractDatasourceEventController(), IOAuthEndPointService {
    private val repository: IRegisterEndpoint = OAuthEndPointRepositories()
    override fun register(
        identity: String,
        password: String,
        confirmPassword: String,
        isEmail: Boolean
    ): Flow<DataSourceEvent> {
        return dataSourceEventControllerEx { repository.register(RegisterRequestDTO2( email =  identity,password= password, confirmPassword = confirmPassword,isEmail)) }
    }

    override fun verifyIdentity(identity: String, pinCode: String): Flow<DataSourceEvent> {
        return dataSourceEventControllerEx { repository.verifyIdentity( VerifyIdentityRequestDto(email = identity,pinCode= pinCode,isEmailIdentity = true)) }
    }

    override fun login(email: String, password: String): Flow<DataSourceEvent> {
        return dataSourceEventControllerEx { repository.login(  LoginRequestDTO2(email = email,password= password,IsEmailIdentity = true)) }
    }

}