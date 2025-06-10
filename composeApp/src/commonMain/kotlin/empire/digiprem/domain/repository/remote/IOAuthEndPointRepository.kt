package empire.digiprem.domain.repository.remote

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import empire.digiprem.data.remote.dto.oauth.RegisterBody
import empire.digiprem.dto.auth.register.RegisterRequestDTO
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.model.ApiResponse2

interface IOAuthEndPointRepository {

    //@POST("")
    suspend fun register( body: RegisterRequestDTO): ApiResponse2<RegisterResponseDTO>
}