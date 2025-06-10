package empire.digiprem.dto.auth.login

import kotlinx.serialization.Serializable


@Serializable
data class LoginRequestDTO(
    val pinCode: String,
    val identity: String
)
