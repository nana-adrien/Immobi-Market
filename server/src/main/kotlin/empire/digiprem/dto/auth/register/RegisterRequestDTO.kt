package empire.digiprem.dto.auth.register

@JvmRecord
data class RegisterRequest2DTO(
    val identity: String,
    val password: String,
    val confirmPassword: String,
    val isEmail: Boolean
)
