package empire.digiprem.controller.auth

import empire.digiprem.dto.auth.login.LoginRequestDTO2
import empire.digiprem.dto.auth.login.LoginResponseDTO2
import empire.digiprem.dto.auth.refresh_token.RefreshTokenReqDTO
import empire.digiprem.dto.auth.refresh_token.RefreshTokenResponseDTO
import empire.digiprem.dto.auth.register.RegisterRequestDTO2
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.dto.auth.register.registerRequestDTO2Validation
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityRequestDto
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityResponseDTO
import empire.digiprem.dto.auth.verify_identity.verifyIdentityValidation
import empire.digiprem.extension.success
import empire.digiprem.extension.validationError
import empire.digiprem.model.ApiResponse2
import empire.digiprem.services.auth.AuthenticationService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/v2/auth")
class AuthenticationController(private val authenticationService: AuthenticationService) {
    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequestDTO2): ApiResponse2<RegisterResponseDTO> {
        val validate=registerRequestDTO2Validation(registerRequest)
        if (!validate.isValid){
            return ApiResponse2.validationError(validate.errors)
        }
        return ApiResponse2.success(authenticationService.register(registerRequest))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequestDTO2): ApiResponse2<LoginResponseDTO2> {
        return ApiResponse2.success(authenticationService.login(loginRequest))
    }

    @PostMapping("/verify-identity")
    fun verifyIdentity(@RequestBody verifyIdentityRequest:VerifyIdentityRequestDto): ApiResponse2<VerifyIdentityResponseDTO> {
        val validate=verifyIdentityValidation(verifyIdentityRequest)
        if (!validate.isValid){
            return ApiResponse2.validationError(validate.errors)
        }
        return ApiResponse2.success(authenticationService.verifyIdentity(verifyIdentityRequest))
    }
    @PostMapping("/refresh-token")
    fun refreshToken(@RequestBody body: RefreshTokenReqDTO): ApiResponse2<RefreshTokenResponseDTO> {
        return ApiResponse2.success(authenticationService.refreshToken(body))
    }
}