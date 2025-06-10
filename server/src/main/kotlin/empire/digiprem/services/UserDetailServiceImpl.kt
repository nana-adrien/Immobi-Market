package empire.digiprem.services

import empire.digiprem.dto.auth.RegisterReqDto
import empire.digiprem.exception.NotFoundException
import empire.digiprem.exception.ValidateTextFiledException
import empire.digiprem.models.*
import empire.digiprem.repositories.UserRepository
import empire.digiprem.repositories.UserRoleRepository
import empire.digiprem.repositories.UsersRepository
import empire.digiprem.service.IUserAccountService
import empire.digiprem.service.IUserAuthenticateService
import empire.digiprem.service.IUserConversationService
import empire.digiprem.service.IUserDetailService
import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.List

@Transactional
@Service
class UserDetailServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val userAccountService: IUserAccountService,
    private val usersRepository: UsersRepository,
    private val userRepository: UserRepository,
    private val roleRepository: UserRoleRepository,
    private val userConversationService: IUserConversationService
) : IUserDetailService, IUserAuthenticateService {

    fun validateIdentity(identity: String, isEmail: Boolean, countryCode: String): Boolean {
        try {
            return if (isEmail) {
                userAccountService.validateEmail(identity)
            } else {
                userAccountService.validatePhoneNumber(identity, countryCode)
            }
        } catch (e: RuntimeException) {
            throw ValidateTextFiledException("identity: " , message="${e.message}")
        }
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        userConversationService.getUserConversations("bonjour $username")
        return userRepository.findByUsername(username).orElseThrow { ValidateTextFiledException("username","Utilisateur $username introuvable") }
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByIdentity(identity: String): UserDetails {
         userAccountService.loadUserByIdentity(identity, true)?.let {
             return it
         }
         throw ValidateTextFiledException("identity","utilisateur introuvable avec identifiant $identity")
    }

    fun register(user: RegisterReqDto): AppUser {
        if (user == null) {
            throw RuntimeException("user is null")
        }
        if (user.username == null || user.password == null) {
            throw RuntimeException("require user and password information")
        }
        if (userRepository.findByUsername(user.username).orElse(null) != null) {
            throw ValidateTextFiledException("username","user exist")
        }
        val password = passwordEncoder.encode(user.password)
        val roles: Collection<UserRole> = List.of(findRoleByName("ADMIN"))
        return userRepository.save(
            AppUser(
                username =  user.username,

                password = password,
                email = user.email,
                phoneNumber = user.phoneNumber,
                enableTwoFactorAuth = user.enableTwoFactorAuth,
                roles = roles
            )
        )
    }

    fun createAccount(userAwaitingVerification: UserAwaitingVerification): Users? {
        val users = userAccountService.createUserAccount(userAwaitingVerification.identifier, userAwaitingVerification.password)
        return users
    }

    fun verifyIdentity(
        identity: String,
        password: String,
        isEmail: Boolean,
        subject: String
    ): UserAwaitingVerification {
        userAccountService.validateEmail(identity)
        return userAccountService.sendVerifyIdentityPinCode(
            identity,
            password,
            isEmail,
            subject,
            VerificationOperation.AUTHENTICATION
        )
    }

    fun verifyPinCode(identity: String, pinCode: String): UserAwaitingVerification? {
        return userAccountService.verifyPinCode(identity, pinCode)
    }

    fun deleteUserAwaitingVerification(userAwaitingVerification: UserAwaitingVerification) {
        userAccountService.deleteUserAwaitingVerification(userAwaitingVerification)
    }

    val roles: Collection<UserRole>
        get() = roleRepository.findAll()

    fun findRoleByName(roleName: String): UserRole {
        return roleRepository.findRoleByName(roleName).orElse(null)
    }

    val allUsers: Collection<AppUser>
        get() = userRepository.findAll()

    fun addUserRole(roleName: String): UserRole {
        var userRole = findRoleByName(roleName)
        if (userRole != null) {
            throw NotFoundException("role <$roleName> exists")
        }
        userRole = UserRole()
        userRole.name = roleName
        return roleRepository.save(userRole)
    }
}