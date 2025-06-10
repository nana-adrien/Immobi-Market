package empire.digiprem.dto.auth.register

import jakarta.validation.constraints.NotBlank


data class RegisterRequestDTO2r(
    val email: String="",
    val password: @NotBlank String="",
    val confirmPassword: @NotBlank String="",
    val IsEmailIdentity: Boolean=false,
    val phoneNumber: String="",
    val countryCode: String="",
    val regionCode: String=""
)