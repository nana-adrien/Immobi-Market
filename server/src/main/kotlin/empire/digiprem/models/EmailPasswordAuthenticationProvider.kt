package empire.digiprem.models

import empire.digiprem.service.IUserDetailService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class EmailPasswordAuthenticationProvider(
    private val passwordEncoder: PasswordEncoder,
    userDetailsService: IUserDetailService
) :
    AuthenticationProvider {
    private val userDetailsService: IUserDetailService = userDetailsService

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val password = authentication.credentials.toString()

        val userDetails: UserDetails = userDetailsService.loadUserByIdentity(authentication.principal.toString())

        if (passwordEncoder.matches(password, userDetails.password)) {
            throw BadCredentialsException("Mot de passe incorrect")
        }
        return EmailAndPasswordAuthenticationToken(userDetails, password, userDetails.authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return EmailAndPasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}
