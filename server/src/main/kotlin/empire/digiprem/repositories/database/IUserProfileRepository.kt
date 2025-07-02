package empire.digiprem.repositories.database

import empire.digiprem.models.database.UserProfile
import empire.digiprem.models.database.User
import empire.digiprem.models.database.UserPhoneNumber
import empire.digiprem.models.database.UserSettings
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface IUserProfileRepository:JpaRepository<UserProfile, UUID> {
   fun findByUser(user: User):Optional<UserProfile>
   fun findByTelephoneUtilisateur(userPhoneNumber: UserPhoneNumber): Optional<UserProfile>
   fun findByNumeroTelephone(phoneNumber: String): Optional<UserProfile>
      fun findByNom(username: String): Optional<UserProfile>

}