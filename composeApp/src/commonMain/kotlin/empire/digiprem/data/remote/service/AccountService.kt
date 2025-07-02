package empire.digiprem.data.remote.service

import empire.digiprem.data.config.AbstractDatasourceEventController
import empire.digiprem.data.config.DataSourceEvent
import empire.digiprem.data.config.DataSourceEventCollector
import empire.digiprem.data.remote.repository.AccountEndPointRepository
import empire.digiprem.domain.repository.remote.IAccountEndPointRepository
import empire.digiprem.domain.servives.remote.IAccountService
import empire.digiprem.dto.account.CreateAccountRequestDTO
import empire.digiprem.enums.Gender
import empire.digiprem.model.PhoneNumber
import kotlinx.coroutines.flow.Flow

class AccountService : AbstractDatasourceEventController(), IAccountService {

    private val repository = AccountEndPointRepository()
    override fun createAccount(
        lastName: String,
        firstName: String,
        gender: Gender,
        phoneNumber: String,
        enabledTowFactorAuth: Boolean
    ): Flow<DataSourceEvent> {
        val body = CreateAccountRequestDTO(
            lastName = lastName,
            firstName = firstName,
            gender = gender,
            contact = PhoneNumber(
                phoneNumber = phoneNumber
            ),
            enabledTwoFactorAuth = enabledTowFactorAuth
        )
        return dataSourceEventControllerEx { repository.createAccount(body) }
    }

}