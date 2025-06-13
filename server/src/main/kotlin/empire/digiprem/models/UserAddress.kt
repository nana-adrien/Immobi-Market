package empire.digiprem.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*


@Entity
data class UserAddress(
    @Id
    private val id: UUID = UUID.randomUUID(),
    var continent: String = "",
    var country: String = "",
    var city: String = "",
    var region: String = "",
    var district: String = "",
)
