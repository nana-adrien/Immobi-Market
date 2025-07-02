package empire.digiprem.dto.auth.login

import empire.digiprem.dto.auth.verify_identity.VerifyIdentityResponseDTO
import empire.digiprem.model.TokensResult
import kotlinx.serialization.Serializable


@Serializable
data class LoginResponseDTO2(
    val enabledTwoFactorAuthentication: Boolean ,
    val isVerifier: Boolean,
    val message: String,
    val tokens: TokensResult?
)