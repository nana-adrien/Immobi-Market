package empire.digiprem.dto.auth.verify_identity

import kotlinx.serialization.Serializable

@Serializable
data class VerifyIdentityResponseDTO(
    val accessToken: String,
    val refreshToken: String
)