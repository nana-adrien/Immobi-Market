package empire.digiprem.models

import empire.digiprem.models.database.User
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import java.util.*


@Entity
class Messages {
    @Id
    private val id: UUID = UUID.randomUUID()
    private var content: String? = null
    private var dateToSend: LocalDateTime? = null

   // @ManyToOne(fetch = FetchType.LAZY)
   // private var sender: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    private var conversations: Conversations? = null
    private val isModify = false

    @ManyToOne(fetch = FetchType.LAZY)
    private val repliedTo: Messages? = null
}
