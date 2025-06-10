package empire.digiprem.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*


@Entity
class UserSettings (
    @Id
     val id: UUID = UUID.randomUUID(),
     val enabledTwoFactorAuthentication:Boolean = true
)
