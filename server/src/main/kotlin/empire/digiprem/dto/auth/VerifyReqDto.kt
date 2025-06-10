package empire.digiprem.dto.auth

@JvmRecord
data class VerifyReqDto(val identifier: String, val code: String)
