package empire.digiprem.domain.servives.remote

import empire.digiprem.data.config.DataSourceEvent
import empire.digiprem.dto.account.CreateAccountRequestDTO
import empire.digiprem.enums.Gender
import kotlinx.coroutines.flow.Flow

interface IAccountService {
    fun createAccount(lastName: String,
                      firstName: String,
                      gender: Gender,
                      phoneNumber: String,
                      enabledTowFactorAuth: Boolean): Flow<DataSourceEvent>
}