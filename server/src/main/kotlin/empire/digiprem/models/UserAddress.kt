package empire.digiprem.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*


@Entity
class UserAddress {
    @Id
    private val id: UUID = UUID.randomUUID()
    private var continent: String=""
    private var country: String=""
    private var city: String=""
    private var region: String=""
    private var district: String=""
}
