package empire.digiprem.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.util.*


@Entity(name = "code_verification")
class CodeVerification (
    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: UUID = UUID.randomUUID(),
     var code: String="",
     var identifier: String="",
    var isEmail: Boolean=false,
     var attempts :Int= 0,
     var generatedAt: LocalDateTime,
     var expiresAt: LocalDateTime
)