package empire.digiprem.models

import empire.digiprem.models.database.User
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDate
import java.util.*


@Entity
class UserReceivedMessage {
    @Id
     val id: UUID = UUID.randomUUID()

    @ManyToOne(fetch = FetchType.EAGER)
     var messages: Messages? = null

    @ManyToOne(fetch = FetchType.EAGER)
     var receivedUser: User? = null
     var receivedDate: LocalDate? = null
}