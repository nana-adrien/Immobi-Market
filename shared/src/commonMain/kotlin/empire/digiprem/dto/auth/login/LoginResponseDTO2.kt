package empire.digiprem.dto.auth.login

import empire.digiprem.dto.auth.verify_identity.VerifyIdentityResponseDTO
import kotlinx.serialization.Serializable


@Serializable
data class LoginResponseDTO2(
    val isVerifier: Boolean,
    val message: String,
    val accessKey: VerifyIdentityResponseDTO?
)