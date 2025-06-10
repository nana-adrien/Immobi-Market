package empire.digiprem.dto.auth.login

import kotlinx.serialization.Serializable


@Serializable
data class LoginRequestDTO2(
    val email: String="",
    val password:  String="",
    val IsEmailIdentity: Boolean=false,
    val phoneNumber: String="",
    val countryCode: String="",
    val regionCode: String=""
)