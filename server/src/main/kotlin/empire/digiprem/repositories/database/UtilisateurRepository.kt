package empire.digiprem.repositories.database

import empire.digiprem.models.database.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface UtilisateurRepository:JpaRepository<User, UUID>{
    fun getUtilisateursByEmail(username: String?): Optional<UserDetails>
    fun removeUtilisateursById(id: UUID)
}