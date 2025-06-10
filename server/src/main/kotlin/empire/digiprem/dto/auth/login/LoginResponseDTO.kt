package empire.digiprem.dto.auth.login

@JvmRecord
data class LoginResponseDTO(
    val accessToken: String,
    val refreshToken: String
)
