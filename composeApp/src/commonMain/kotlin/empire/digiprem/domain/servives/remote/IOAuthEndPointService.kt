package empire.digiprem.domain.servives.remote

import empire.digiprem.data.config.DataSourceEvent
import kotlinx.coroutines.flow.Flow

interface IOAuthEndPointService {
    fun register(
        identity: String,
        password: String,
        confirmPassword: String,
        isEmail: Boolean
    ): Flow<DataSourceEvent>

    fun verifyIdentity(
        identity: String = "",
         pinCode: String = "",
    ): Flow<DataSourceEvent>

    fun login(
        email: String = "",
         password: String,
    ): Flow<DataSourceEvent>
    fun refreshToken(
        refreshToken: String = "",
    ): Flow<DataSourceEvent>
}