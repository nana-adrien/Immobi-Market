package empire.digiprem.models

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import java.util.*


@Entity
class Message {
    @Id
    private val id: UUID = UUID.randomUUID()
    private var content: String? = null
    private var dateToSend: LocalDateTime? = null

    @ManyToOne(fetch = FetchType.LAZY)
    private var sender: Users? = null

    @ManyToOne(fetch = FetchType.LAZY)
    private var conversation: Conversation? = null
    private val isModify = false

    @ManyToOne(fetch = FetchType.LAZY)
    private val repliedTo: Message? = null
}
