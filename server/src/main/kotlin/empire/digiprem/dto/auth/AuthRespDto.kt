package empire.digiprem.dto.auth;

@JvmRecord
public data class AuthRespDto(
    val authenticationKey:String="",
    val  start:String="",
    val  token:String="", val username:String="",
){}
