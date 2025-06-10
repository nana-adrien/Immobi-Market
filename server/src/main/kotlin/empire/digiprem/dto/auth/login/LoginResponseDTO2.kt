package empire.digiprem.dto.auth.login

import empire.digiprem.dto.auth.verify_identity.VerifyIdentityResponseDTO

@JvmRecord
data class LoginResponseDTO23(
    val isVerifier: Boolean,
    val message: String,
    val accessKey: VerifyIdentityResponseDTO?
)