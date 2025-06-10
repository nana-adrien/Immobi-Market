package empire.digiprem.services

import empire.digiprem.dto.auth.login.LoginRequestDTO2
import empire.digiprem.dto.auth.login.LoginResponseDTO2
import empire.digiprem.dto.auth.register.RegisterRequestDTO2
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityRequestDto
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityResponseDTO
import empire.digiprem.exception.ValidateTextFiledException
import empire.digiprem.models.*
import empire.digiprem.service.INotificationService
import empire.digiprem.service.IUserAccountService
import empire.digiprem.utils.JwtTokenUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val  jwtTokenUtil: JwtTokenUtil,
    private val userDetailService: UserDetailServiceImpl,
    private val userAccountService: IUserAccountService,
    private val notificationService: INotificationService,
    private val userAwaitingVerificationService: UserAwaitingVerificationService,
    private val authenticationManager: AuthenticationManager
) {

    fun register(registerRequest: RegisterRequestDTO2): RegisterResponseDTO {
        // check if password equals confirmation password
        require(
            registerRequest.password.equals(registerRequest.confirmPassword)
        ) { "Passwords do not match to confirm password" }
        // check if is valid password or identity
        userAccountService.validatePassword(registerRequest.password)
        val identity =
            if (registerRequest.IsEmailIdentity) userAccountService.validateEmail2(registerRequest.email)
            else userAccountService.validatePhoneNumber2(registerRequest.phoneNumber, registerRequest.countryCode)

        // create a new awaiting user
        if (userAwaitingVerificationService.loadUserAwaitingVerificationByIdentity(identity) != null) {
            throw ValidateTextFiledException("identity","impossible de vous  enrgistrer car vous avez deja une procedure en attente de verification")
        }
        val userAwaitingVerification =
            if (registerRequest.IsEmailIdentity) userAwaitingVerificationService.createAwaitingUser(
                registerRequest.email,
                registerRequest.password,
                VerificationOperation.REGISTRATION
            ) else userAwaitingVerificationService.createAwaitingUser(
                registerRequest.phoneNumber, registerRequest.countryCode,
                registerRequest.password, VerificationOperation.REGISTRATION
            )
        // generate  a verification pin code
        val verificationPinCode = userAccountService.generateVerificationCode()
        // send code to de user identity
        notificationService.sendNotification(
            userAwaitingVerification.identifier,
            "Registration",
            "votre code de verification est : $verificationPinCode",
            if (userAwaitingVerification.isEmail) NotificationCanal.EMAIL else NotificationCanal.SMS
        )
        // add pin code to the user awaiting user create
        userAwaitingVerification.code = verificationPinCode

        // add user to the awaiting file
        requireNotNull(userAwaitingVerificationService.addToAwaitingFile(userAwaitingVerification)) { " Failed to registration user" }

        val canal = if (userAwaitingVerification.isEmail) "email " + userAwaitingVerification.identifier else "mobile " + userAwaitingVerification.countryCode.trim { it <= ' ' } + userAwaitingVerification.identifier
        return RegisterResponseDTO("un code d'authentification vous a ete envoyer a l'adresse $canal", email = userAwaitingVerification.identifier)
    }

    fun verifyIdentity(loginRequest: VerifyIdentityRequestDto): VerifyIdentityResponseDTO {
        // get user identifier
        val identity =
            if (loginRequest.isEmailIdentity) userAccountService.validateEmail2(loginRequest.email) else userAccountService.validatePhoneNumber2(
                loginRequest.phoneNumber,
                loginRequest.countryCode
            )

        // load user awaiting by identifier
        val userAwaitingVerification = userAwaitingVerificationService.loadUserAwaitingVerificationByIdentity(identity)
            ?: throw ValidateTextFiledException("identity","utilisateur introuvable ou session d'utilisateur expirer")

        //check if user awaiting exist
        // check if  login pin code equals user awaiting pin code
        if (!userAwaitingVerificationService.verifyCode(userAwaitingVerification.code, loginRequest.pinCode)) {
            throw ValidateTextFiledException(field ="pinCode",message="Code de verification incorrecte")
        }
        // get the  identify user
        val user = userAwaitingVerificationService.getIdentifyUser(userAwaitingVerification.identifier)
        when (userAwaitingVerification.operation) {
            VerificationOperation.REGISTRATION -> {
                val userSaved = userAccountService.createUserAccount(user)
                userSaved?.let {
                    val accessToken: String = jwtTokenUtil.generateToken(userSaved)
                    return VerifyIdentityResponseDTO(
                        accessToken,
                        ""
                    )
                }

            }

            VerificationOperation.AUTHENTICATION -> {
                return VerifyIdentityResponseDTO(
                    jwtTokenUtil.generateToken(user),
                    ""
                )
            }

            else -> throw RuntimeException("Aucune session d'authentification en cour pour cette utilisateur")
        }
        throw RuntimeException("Aucune session d'authentification en cour pour cette utilisateur")
    }

    fun login(loginRequest: LoginRequestDTO2): LoginResponseDTO2 {
        val identity =
            if (loginRequest.IsEmailIdentity) userAccountService.validateEmail2(loginRequest.email) else userAccountService.validatePhoneNumber2(
                loginRequest.phoneNumber,
                loginRequest.countryCode
            )
        userAccountService.validatePassword(loginRequest.password)

        val authentication = authenticationManager.authenticate(
            IdentityAndPasswordAuthenticationToken(
                identity,
                loginRequest.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val users = userAccountService.loadUserByIdentityEx(
            identity,
            loginRequest.countryCode,
            loginRequest.IsEmailIdentity
        )
            ?: throw RuntimeException("utilisateur introuvable")
        if (users.settings != null && users.settings?.enabledTwoFactorAuthentication==true) {
            val userAwaitingVerification =
                if (loginRequest.IsEmailIdentity) userAwaitingVerificationService.createAwaitingUser(
                    loginRequest.email,
                    loginRequest.password,
                    VerificationOperation.AUTHENTICATION
                ) else userAwaitingVerificationService.createAwaitingUser(
                    loginRequest.phoneNumber,
                    loginRequest.countryCode,
                    loginRequest.password,
                    VerificationOperation.AUTHENTICATION
                )

            val verificationPinCode = userAccountService.generateVerificationCode()
            notificationService.sendNotification(
                userAwaitingVerification.identifier,
                "Authentication " + users.name,
                "",
                if (userAwaitingVerification.isEmail) NotificationCanal.EMAIL else NotificationCanal.SMS
            )
            userAwaitingVerification.code = verificationPinCode
            requireNotNull(userAwaitingVerificationService.addToAwaitingFile(userAwaitingVerification)) { " Failed to Authenticate user" }

            val canal =
                if (userAwaitingVerification.isEmail) "email " + userAwaitingVerification.identifier else "mobile " + userAwaitingVerification.identifier

            return LoginResponseDTO2(false, "un code d'authentification vous a ete envoyer a l'adresse $canal", null)
        } else {
            val token: String = jwtTokenUtil.generateToken(users)
            return LoginResponseDTO2(true, "", VerifyIdentityResponseDTO(token, ""))
        }
    }
}