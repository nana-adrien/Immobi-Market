package empire.digiprem.data.remote.service

import empire.digiprem.data.config.AbstractDatasourceEventController
import empire.digiprem.data.config.DataSourceEvent
import empire.digiprem.data.remote.repository.ProfileEndPointRepository
import empire.digiprem.domain.servives.remote.IProfileService
import empire.digiprem.dto.profile.UpdateProfileRequest
import empire.digiprem.model.PhoneNumber
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

class ProfileService:IProfileService,AbstractDatasourceEventController(){


    private val repository: ProfileEndPointRepository= ProfileEndPointRepository()

    override suspend fun getProfile(): Flow<DataSourceEvent> {
       return dataSourceEventControllerEx { repository.getProfile() }
    }

    override suspend fun updateProfile(
        name: String?,
        firstName: String?,
        profileUrl: String?,
        bornAt: LocalDate?,
        userPhoneNumber: PhoneNumber?,
        enableTwoFactorAuth: Boolean?
    ): Flow<DataSourceEvent> {
       return dataSourceEventControllerEx {
           val body= UpdateProfileRequest(
               name = name,
               firstName = firstName,
               profileUrl = profileUrl,
               bornAt = bornAt,
               userPhoneNumber = userPhoneNumber,
               enableTwoFactorAuth = enableTwoFactorAuth
           )
           repository.updateProfile(body) }
    }


}