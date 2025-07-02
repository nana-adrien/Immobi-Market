package empire.digiprem.service.database

import org.springframework.security.core.userdetails.UserDetailsService

interface UtilisateurService:UserDetailsService {
    abstract fun validatePassword(password: String)


}