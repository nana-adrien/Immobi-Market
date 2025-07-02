package empire.digiprem.models.database

import empire.digiprem.enums.VerificationOperation
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.util.*


@Entity
class UserAwaitingVerification (
    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: UUID = UUID.randomUUID(),
    var code: String="",
    var identifier: String="",
    var password: String="",
    var countryCode: String="",
    var regionCode: String="",
    var subject: String="",
    var isEmail: Boolean=false,
    var attempts :Int= 0,

    @Enumerated(EnumType.STRING) var operation: VerificationOperation,
    var generatedAt: LocalDateTime,
    var expiresAt: LocalDateTime,
)