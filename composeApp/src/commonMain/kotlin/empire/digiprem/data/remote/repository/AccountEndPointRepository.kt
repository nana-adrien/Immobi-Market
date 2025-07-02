package empire.digiprem.data.remote.repository

import empire.digiprem.data.remote.config.KtorfitServiceCreator
import empire.digiprem.domain.repository.remote.IAccountEndPointRepository
import empire.digiprem.dto.account.CreateAccountRequestDTO
import empire.digiprem.dto.account.CreateAccountResponse
import empire.digiprem.model.ApiResponse2
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AccountEndPointRepository:IAccountEndPointRepository ,KoinComponent{

    private val ktorfitService:KtorfitServiceCreator by inject()

    override suspend fun createAccount(body: CreateAccountRequestDTO): ApiResponse2<CreateAccountResponse> {
        return ktorfitService.apiClient().create<IAccountEndPointRepository>().createAccount(body)
    }
}