package empire.digiprem.repositories

import empire.digiprem.models.UserSettings
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserSettingsRepository : JpaRepository<UserSettings, UUID>
