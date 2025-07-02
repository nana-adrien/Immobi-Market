package empire.digiprem.models.database

import jakarta.persistence.*
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@Entity
class UserPhoneNumber @OptIn(ExperimentalUuidApi::class) constructor(
    @OneToOne
    @MapsId("id")
    val userProfile: UserProfile,
    @Id
     val id: UUID ,
    var phoneNumber: String = "",
    var countryCode: String =  "",
    var region: String =  "",
)