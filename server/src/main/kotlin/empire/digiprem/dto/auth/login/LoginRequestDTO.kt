package empire.digiprem.dto.auth.login

@JvmRecord
data class LoginRequestDTO(
    val pinCode: String,
    val identity: String
)
