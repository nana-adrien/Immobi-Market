package empire.digiprem.service.database

import empire.digiprem.models.database.User
import empire.digiprem.repositories.database.UtilisateurRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull
import kotlin.uuid.ExperimentalUuidApi

@Service
class UtilisateurServiceImpl(
    val utilisateurRepository: UtilisateurRepository
) : UtilisateurService {
    override fun validatePassword(password: String) {
        TODO("Not yet implemented")
    }

    override fun loadUserByUsername(username: String?): UserDetails? {
        return utilisateurRepository.getUtilisateursByEmail(username).getOrNull()
    }

    fun createUser(user: User): User {
      return  utilisateurRepository.save(user)
    }
    @OptIn(ExperimentalUuidApi::class)
    fun removeUser(user: User){
        //utilisateurRepository.removeUtilisateursById(user)
    }
}