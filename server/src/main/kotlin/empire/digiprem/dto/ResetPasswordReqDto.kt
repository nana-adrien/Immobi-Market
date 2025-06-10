package empire.digiprem.dto

@JvmRecord
data class ResetPasswordReqDto(
    val identity: String,
    val isEmail: Boolean,
    val newPassword: String
)
