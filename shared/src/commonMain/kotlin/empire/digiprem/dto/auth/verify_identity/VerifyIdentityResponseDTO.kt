package empire.digiprem.dto.auth.verify_identity

import empire.digiprem.model.TokensResult
import kotlinx.serialization.Serializable

@Serializable
data class VerifyIdentityResponseDTO(
    val tokens: TokensResult,
)