package empire.digiprem.dto

@JvmRecord
data class ForgotPasswordReq(val identity: String, val isEmail: Boolean)
