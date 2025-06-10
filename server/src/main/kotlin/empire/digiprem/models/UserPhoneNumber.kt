package empire.digiprem.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*


@Entity
class UserPhoneNumber(
    @Id
     val id: UUID = UUID.randomUUID(),
    var phoneNumber: String = "",
    var countryCode: String =  "",
     var region: String =  "",
)