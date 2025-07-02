package empire.digiprem.models

import empire.digiprem.service.IUserDetailService2
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class IdentityAndPasswordAuthenticationProvider(
    private val passwordEncoder: PasswordEncoder,
    userDetailService: IUserDetailService2
) :
    AuthenticationProvider {
    private val userDetailService: IUserDetailService2 = userDetailService

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication? {
        if (authentication !is IdentityAndPasswordAuthenticationToken) {
            return null
        }
        val identity = authentication.getPrincipal().toString()
        val password = authentication.getCredentials().toString()
        val user: UserDetails = userDetailService.loadUserByIdentity(identity)
        if (!passwordEncoder.matches(password, user.password)) {
            throw BadCredentialsException("Mot de passe incorrect " + user.password)
        }
        return IdentityAndPasswordAuthenticationToken(user, password, user.authorities)
    }


    override fun supports(authentication: Class<*>?): Boolean {
        return IdentityAndPasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
