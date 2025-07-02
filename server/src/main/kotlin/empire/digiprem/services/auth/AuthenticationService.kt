package empire.digiprem.services.auth

import empire.digiprem.dto.auth.login.LoginRequestDTO2
import empire.digiprem.dto.auth.login.LoginResponseDTO2
import empire.digiprem.dto.auth.refresh_token.RefreshTokenReqDTO
import empire.digiprem.dto.auth.refresh_token.RefreshTokenResponseDTO
import empire.digiprem.dto.auth.register.RegisterRequestDTO2
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityRequestDto
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityResponseDTO
import empire.digiprem.enums.NotificationCanal
import empire.digiprem.enums.VerificationOperation
import empire.digiprem.exception.ValidateTextFiledException
import empire.digiprem.model.TokensResult
import empire.digiprem.models.*
import empire.digiprem.models.database.User
import empire.digiprem.models.database.UserToken
import empire.digiprem.repositories.database.UserSettingsRepository
import empire.digiprem.repositories.database.IUserProfileRepository
import empire.digiprem.service.INotificationService
import empire.digiprem.service.IUserAccountService
import empire.digiprem.services.UserDetailService2Impl
import empire.digiprem.utils.JwtTokenUtil
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import kotlin.uuid.ExperimentalUuidApi
@Transactional
@Service
class AuthenticationService(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailService: UserDetailService2Impl,
    private val userTokenService: UserTokenService,
    private val userAccountService: IUserAccountService,
    private val userProfileRepository: IUserProfileRepository,
    private val notificationService: INotificationService,
    private val userSettingsRepository: UserSettingsRepository,
    private val userAwaitingVerificationService: UserAwaitingVerificationService,
    private val authenticationManager: AuthenticationManager
) {

    fun register(registerRequest: RegisterRequestDTO2): RegisterResponseDTO {

       if (userAccountService. loadUserByIdentity(registerRequest.email,true) != null) {
            throw RuntimeException("un utilisateur utilise deja cette adresse email")
        }

        // check if password equals confirmation password
        require(registerRequest.password.equals(registerRequest.confirmPassword)) { "Passwords do not match to confirm password" }
        // check if is valid password or identity
        userAccountService.validatePassword(registerRequest.password)
        val identity =
            if (registerRequest.IsEmailIdentity) userAccountService.validateEmail2(registerRequest.email)
            else userAccountService.validatePhoneNumber2(registerRequest.phoneNumber, registerRequest.countryCode)

        // create a new awaiting user
        if (userAwaitingVerificationService.loadUserAwaitingVerificationByIdentity(identity) != null) {
            throw ValidateTextFiledException(
                "identity",
                "impossible de vous  enrgistrer car vous avez deja une procedure en attente de verification"
            )
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

        val canal =if (userAwaitingVerification.isEmail) "email " + userAwaitingVerification.identifier else "mobile " + userAwaitingVerification.countryCode.trim { it <= ' ' } + userAwaitingVerification.identifier
        return RegisterResponseDTO(
            "un code d'authentification vous a ete envoyer a l'adresse $canal",
            email = userAwaitingVerification.identifier
        )
    }
    @OptIn(kotlin.uuid.ExperimentalUuidApi::class)
    fun verifyIdentity(loginRequest: VerifyIdentityRequestDto): VerifyIdentityResponseDTO {

        val identity =  if (loginRequest.isEmailIdentity) userAccountService.validateEmail2(loginRequest.email)
            else userAccountService.validatePhoneNumber2(
                loginRequest.phoneNumber,
                loginRequest.countryCode
            )


        val userAwaitingVerification = userAwaitingVerificationService.loadUserAwaitingVerificationByIdentity(identity)
            ?: throw ValidateTextFiledException("identity", "utilisateur introuvable ou session verification d'utilisateur expirer")

        //check if user awaiting exist
        // check if  login pin code equals user awaiting pin code
        if (!userAwaitingVerificationService.verifyCode(userAwaitingVerification.code, loginRequest.pinCode)) {
            throw ValidateTextFiledException(field = "pinCode", message = "Code de verification incorrecte")
        }
        // get the  identify user
        val user = userAwaitingVerificationService.getIdentifyUser(userAwaitingVerification.identifier)
        val userToken = UserToken()
        when (userAwaitingVerification.operation) {
            VerificationOperation.REGISTRATION -> {
                val userSaved = userAccountService.createUserAccount(user)
                userSaved.let {
                    return  getVerifyIdentityResponse(userSaved, userToken)
                }
            }
            VerificationOperation.AUTHENTICATION -> {
                return getVerifyIdentityResponse(user, userToken)
            }

            else -> throw RuntimeException("Aucune session d'authentification en cour pour cette utilisateur")
        }
        throw ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Aucune session d'authentification en cours pour cet utilisateur"
        )

    }

    @OptIn(ExperimentalUuidApi::class)
    private fun getVerifyIdentityResponse(
        user: User,
        userToken: UserToken
    ): VerifyIdentityResponseDTO {
        var userToken1 = userToken
        val accessToken = jwtTokenUtil.generateAccessToken(user, userToken1.id.toString())
        val refreshToken = jwtTokenUtil.generateRefreshToken(user, userToken1.id.toString())
        userToken1 = userToken1.copy(
            accessToken = accessToken,
            refreshToken = refreshToken,
            user = user
        )
        userTokenService.saveToken(userToken1)
        return VerifyIdentityResponseDTO(
            TokensResult(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        )
    }

    @OptIn(ExperimentalUuidApi::class)
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
        ) ?: throw RuntimeException("utilisateur introuvable")

        if (userSettingsRepository.existsById(users.id) && userSettingsRepository.getReferenceById(users.id).enabledTwoFactorAuthentication) {
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

            val userProfile=if (userProfileRepository.existsById(users.id)) userProfileRepository.getReferenceById(users.id) else null

            val verificationPinCode = userAccountService.generateVerificationCode()
            notificationService.sendNotification(
                userAwaitingVerification.identifier,
                "Authentication " + userProfile?.prenom ,
                "",
                if (userAwaitingVerification.isEmail) NotificationCanal.EMAIL else NotificationCanal.SMS
            )
            userAwaitingVerification.code = verificationPinCode
            requireNotNull(userAwaitingVerificationService.addToAwaitingFile(userAwaitingVerification)) { " Failed to Authenticate user" }

            val canal =
                if (userAwaitingVerification.isEmail) "email " + userAwaitingVerification.identifier else "mobile " + userAwaitingVerification.identifier

            return LoginResponseDTO2(
                enabledTwoFactorAuthentication = true,
                false,
                "un code d'authentification vous a ete envoyer a l'adresse $canal",
                null
            )
        }
        else {

            var userToken = UserToken()
            val accessToken = jwtTokenUtil.generateAccessToken(users, userToken.id.toString())
            val refreshToken = jwtTokenUtil.generateRefreshToken(users, userToken.id.toString())
            userToken = userToken.copy(
                accessToken = accessToken,
                refreshToken = refreshToken,
                user = users
            )
            userTokenService.saveToken(userToken)
            return LoginResponseDTO2(
                enabledTwoFactorAuthentication = false,
                true,
                "", TokensResult(
                    accessToken = accessToken,
                    refreshToken = refreshToken
                )

            )
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    fun refreshToken(requestBody: RefreshTokenReqDTO): RefreshTokenResponseDTO {
        val user= userDetailService.loadUserByIdentity(jwtTokenUtil.getUsername(requestBody.refreshToken)) as User
        var userToken= userTokenService.getUserTokenByUserAndId(user,jwtTokenUtil.getId(requestBody.refreshToken))
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token ")
        if (userToken.refreshToken != requestBody.refreshToken) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token is not valid")
        }
        if (!jwtTokenUtil.getType(requestBody.refreshToken).isRefreshToken()) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token type")
        }
        if (jwtTokenUtil.isTokenExpired(requestBody.refreshToken)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token is expired")
        }

        val accessToken = jwtTokenUtil.generateAccessToken(user, userToken.id.toString())
       // val refreshToken = jwtTokenUtil.generateRefreshToken(user, userToken.id.toString())
        userToken = userToken.copy(
            accessToken = accessToken,
        )
        userTokenService.saveToken(userToken)
        return RefreshTokenResponseDTO(
            TokensResult(
                accessToken = accessToken,
                refreshToken =userToken.refreshToken
            )
        )
    }
}