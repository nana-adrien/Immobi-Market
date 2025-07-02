/*
package empire.digiprem.controller.auth

import empire.digiprem.dto.auth.*
import empire.digiprem.dto.auth.authentication.AuthenticationRequestDTO
import empire.digiprem.dto.auth.authentication.AuthenticationResponseDTO
import empire.digiprem.dto.auth.login.LoginRequestDTO
import empire.digiprem.dto.auth.login.LoginResponseDTO
import empire.digiprem.dto.auth.register.RegisterRequestDTO
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.dto.auth.register.registerRequestDTOValidation
import empire.digiprem.enums.VerificationOperation
import empire.digiprem.extension.error
import empire.digiprem.extension.success
import empire.digiprem.model.ApiResponse2
import empire.digiprem.models.*
import empire.digiprem.services.CodeVerificationService
import empire.digiprem.services.auth.TwoFactorAuthenticationService
import empire.digiprem.services.UserDetailService2Impl
import empire.digiprem.utils.JwtTokenUtil
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@Tag(name = "0auth")
@RestController
@RequestMapping("api/v3/auth")
class AuthController(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailService: UserDetailService2Impl,
    private val authenticationManager: AuthenticationManager,
    private val twoFactorAuthenticationService: TwoFactorAuthenticationService,
    private val codeVerificationService: CodeVerificationService
) {
    @PostMapping("/loginTest")
    fun loginTest(@RequestBody authReqDto: AuthReqDto): ApiResponse2<AuthRespDto> {
        // AuthController.log.info("Login request received for user: {}", authReqDto.identity)
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authReqDto.identity,
                authReqDto.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val userDetails = userDetailService.loadUserByUsername(authReqDto.identity) as AppUser
        if (userDetails.enableTwoFactorAuth) {
            return towFactorAuth(userDetails)
        }
        return generateTokenAuthenticationResponse(userDetails)
    }

    @PostMapping("/authenticateTest")
    fun authenticate2(@RequestBody authReqDto: AuthReqDto): ApiResponse2<AuthRespDto> {
        if (userDetailService.validateIdentity(
                authReqDto.identity,
                authReqDto.isEmail,
                authReqDto.phoneNumberCountryCode
            )
        ) {
            throw RuntimeException("entrer un identifiant valide")
        }
        val authentication = authenticationManager.authenticate(
            IdentityAndPasswordAuthenticationToken(
                authReqDto.identity,
                authReqDto.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val user = userDetailService.loadUserByUsername(authReqDto.identity) as User
        if (user.settings?.enabledTwoFactorAuthentication == true) {
            return towFactorAuth(user)
        }
        return generateTokenAuthenticationResponse(user)
    }

    @PostMapping("/register2")
    fun register2(@RequestBody registerReqDto: @Valid RegisterReqDto): ApiResponse2<RegisterRespDto> {
        val savedAppUser = userDetailService.register(registerReqDto)
        val registerRespDto =
            RegisterRespDto(savedAppUser.id, savedAppUser.username, savedAppUser.email, savedAppUser.roles)
        return ApiResponse2.success(registerRespDto)
    }


    @PostMapping("/authenticate")
    fun authentication(@RequestBody authenticationRequestDTO: @Valid AuthenticationRequestDTO): ApiResponse2<AuthenticationResponseDTO> {
        val authentication = authenticationManager.authenticate(
            IdentityAndPasswordAuthenticationToken(
                authenticationRequestDTO.identity,
                authenticationRequestDTO.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val user = userDetailService.loadUserByIdentity(authenticationRequestDTO.identity) as User
        if (user.settings?.enabledTwoFactorAuthentication==true) {
            val userAwaitingVerification = userDetailService.verifyIdentity(
                authenticationRequestDTO.identity,
                authenticationRequestDTO.password,
                true,
                "Authentification"
            )
            return ApiResponse2.success(
                AuthenticationResponseDTO(
                    isVerifier = false,
                    message = "un code de verification vous a ete envoyer via l'adresse" + userAwaitingVerification.identifier
                )
            )
        }
        val token = jwtTokenUtil.generateAccessToken(user,"1")
        return ApiResponse2.success(
            AuthenticationResponseDTO(isVerifier = false, accessKey = LoginResponseDTO(token, ""))
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody registerRequestDTO:  RegisterRequestDTO): ApiResponse2<RegisterResponseDTO> {
        val validation = registerRequestDTOValidation(registerRequestDTO)
        if (validation is io.konform.validation.Invalid) {
            return ApiResponse2.error(validation.errors.map {
                ApiResponse2.ErrorMessage(
                    field = it.dataPath,
                    message = it.message
                )
            }
            )
        }
        val userAwaitingVerification = userDetailService.verifyIdentity(
            registerRequestDTO.identity,
            registerRequestDTO.password,
            true,
            "Inscription"
        )
        return ApiResponse2.success(RegisterResponseDTO("votre code de verification vous a ete envoyer via l'adresse " + userAwaitingVerification.identifier))

    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDTO: @Valid LoginRequestDTO): ApiResponse2<LoginResponseDTO> {
        val userAwaitingVerification =
            userDetailService.verifyPinCode(loginRequestDTO.identity, loginRequestDTO.pinCode)
                ?: throw RuntimeException("user not found")
        var user: User?
        if (userAwaitingVerification.operation == VerificationOperation.REGISTRATION) {
            user =
                userDetailService.createAccount(userAwaitingVerification) ?: throw RuntimeException("user not Create")
        } else {
            user = userDetailService.loadUserByIdentity(userAwaitingVerification.identifier) as User
           */
/* if (users == null) {
                throw RuntimeException("user not found")
            }*//*

        }
        userDetailService.deleteUserAwaitingVerification(userAwaitingVerification)
        val token = jwtTokenUtil.generateAccessToken(user,"1")
        return ApiResponse2.success(LoginResponseDTO(token, ""))
    }


    @PostMapping("/verify-code")
    fun verifier(@RequestBody verifyReqDto: VerifyReqDto): ApiResponse2<AuthRespDto> {
         println("**************\n verifyReqDto = ${verifyReqDto.toString()}")
        val codeVerification = codeVerificationService.getCodeVerification(verifyReqDto.identifier)
        println("**************\n codeVerification = ${codeVerification.toString()}")
        if (!codeVerificationService.verifyCode(verifyReqDto.code, codeVerification.code)) {
            throw RuntimeException("Code verification failed")
        } else {
            codeVerificationService.deleteCodeVerification(codeVerification)
        }
        val userDetails = userDetailService.loadUserByIdentity(verifyReqDto.identifier)
        return generateTokenAuthenticationResponse(userDetails)
    }

    @PostMapping("/resend-code")
    fun resendCode(@RequestBody resendCodeReqDto: ResendCodeReqDto): ApiResponse2<AuthRespDto> {
        val codeVerification = codeVerificationService.getCodeVerification(resendCodeReqDto.identifier)
            ?: throw RuntimeException(" identiter introuvable")
        codeVerificationService.resendVerificationCode(codeVerification, "Login verificated ")
        return ApiResponse2.success(
            AuthRespDto(
                token = "votre code de verification vous a ete envoyer via l'adresse " + codeVerification.identifier,
                username = codeVerification.identifier
            )
        )
    }


    @PostMapping("/add_role")
    fun addRole(@RequestBody roleReqDto: RoleReqDto): ApiResponse2<UserRole> {
        return ApiResponse2.success(userDetailService.addUserRole(roleReqDto.roleName))
    }

    @get:GetMapping("/list_all_roles")
    val roles: ApiResponse2<Collection<UserRole>>
        get() = ApiResponse2.success(userDetailService.roles)

    @get:GetMapping("/list_all_users")
    val users: ApiResponse2<Collection<AppUser>>
        get() = ApiResponse2.success(userDetailService.allUsers)


    private fun generateTokenAuthenticationResponse(userDetails: UserDetails): ApiResponse2<AuthRespDto> {
        val token = jwtTokenUtil.generateAccessToken(userDetails,"1")
        return ApiResponse2.success(AuthRespDto(token = token, username = userDetails.username))
    }

    private fun towFactorAuth(userDetails: AppUser): ApiResponse2<AuthRespDto> {
        val codeVerification = CodeVerification(
            attempts = 5,
            identifier = userDetails.email,
            generatedAt = LocalDateTime.now(),
            expiresAt = LocalDateTime.now().plusMinutes(5),
            isEmail = true
        )
        codeVerificationService.sendVerificationCode(codeVerification, "Login verificated ")
        return ApiResponse2.success(
            AuthRespDto(
                token = "votre code de verification vous a ete envoyer via l'adresse" + codeVerification.identifier,
                username = codeVerification.identifier
            )
        )
    }

    private fun towFactorAuth(userDetails: User): ApiResponse2<AuthRespDto> {
        val codeVerification = CodeVerification(
            identifier = userDetails.email,
            generatedAt = LocalDateTime.now(),
            expiresAt = LocalDateTime.now().plusMinutes(5),
            isEmail = true,
        )

        codeVerificationService.sendVerificationCode(codeVerification, "Login verificated ")
        return ApiResponse2.success(
            AuthRespDto(
                token = "votre code de verification vous a ete envoyer via l'adresse" + codeVerification.identifier,
                username = codeVerification.identifier
            )
        )
    }
}*/
