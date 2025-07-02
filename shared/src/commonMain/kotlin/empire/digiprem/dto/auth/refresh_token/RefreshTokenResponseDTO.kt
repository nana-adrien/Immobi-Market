package empire.digiprem.dto.auth.refresh_token

import empire.digiprem.model.TokensResult
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponseDTO(val tokensResult: TokensResult)