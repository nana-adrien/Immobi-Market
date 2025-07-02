package empire.digiprem.repositories.database

import empire.digiprem.models.database.User
import empire.digiprem.models.database.UserSettings
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserSettingsRepository : JpaRepository<UserSettings, UUID>{
    fun findByUser(user: User):Optional<UserSettings>
}
