package empire.digiprem.domain.repository.remote

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.Multipart
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Part
import empire.digiprem.dto.account.CreateAccountRequestDTO
import empire.digiprem.dto.account.CreateAccountResponse
import empire.digiprem.model.ApiResponse2
import io.ktor.http.content.*

interface IAccountEndPointRepository {

    @POST("v2/account/create")
    suspend fun createAccount(
        @Body body: CreateAccountRequestDTO
    ):ApiResponse2<CreateAccountResponse>
}