package empire.digiprem.domain.servives.remote

import empire.digiprem.data.config.DataSourceEvent
import empire.digiprem.model.PhoneNumber
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface IProfileService {

    suspend fun getProfile(): Flow<DataSourceEvent>

    suspend fun updateProfile(
        name: String? = null,
        firstName: String? = null,
        profileUrl: String? = null,
        bornAt: LocalDate? = null,
        userPhoneNumber: PhoneNumber? = null,
        enableTwoFactorAuth: Boolean? = null
    ): Flow<DataSourceEvent>
}