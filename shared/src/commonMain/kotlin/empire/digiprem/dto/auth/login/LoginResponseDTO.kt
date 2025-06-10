package empire.digiprem.dto.auth.login

import kotlinx.serialization.Serializable


@Serializable
data class LoginResponseDTO(
    val accessToken: String,
    val refreshToken: String
)
