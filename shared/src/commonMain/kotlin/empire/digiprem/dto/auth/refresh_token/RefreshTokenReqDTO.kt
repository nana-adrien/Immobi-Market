package empire.digiprem.dto.auth.refresh_token

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenReqDTO (
    val refreshToken: String,
)