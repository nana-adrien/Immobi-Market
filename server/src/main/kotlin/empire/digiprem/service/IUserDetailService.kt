package empire.digiprem.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

interface IUserDetailService : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    fun loadUserByIdentity(identity: String): UserDetails
}
