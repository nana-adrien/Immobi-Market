package empire.digiprem.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException


interface IUserAuthenticateService {
    @Throws(UsernameNotFoundException::class)
    fun loadUserByIdentity(identity: String): UserDetails
}
