package empire.digiprem.repositories

import empire.digiprem.models.UserSettings
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface UserSettingsRepository : JpaRepository<UserSettings, UUID>
