package empire.digiprem.dto.auth.authentication

import empire.digiprem.dto.auth.login.LoginResponseDTO


@JvmRecord
data class AuthenticationResponseDTO(val isVerifier: Boolean, val message: String="", val accessKey: LoginResponseDTO?=null)
