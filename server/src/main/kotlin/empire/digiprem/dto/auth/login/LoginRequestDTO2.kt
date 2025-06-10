package empire.digiprem.dto.auth.login

import jakarta.validation.constraints.NotBlank


@JvmRecord
data class LoginRequestDTO23(
    val email: String="",
    val password: @NotBlank String="",
    val IsEmailIdentity: Boolean=false,
    val phoneNumber: String="",
    val countryCode: String="",
    val regionCode: String=""
)