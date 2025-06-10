package empire.digiprem.dto.auth.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDTO(
    val confirmationMessage: String="",
    val email: String=""
)
