package empire.digiprem.dto


data class ResetPasswordReqDto(
    val identity: String,
    val isEmail: Boolean,
    val newPassword: String
)
