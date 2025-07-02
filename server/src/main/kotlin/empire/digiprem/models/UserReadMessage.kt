package empire.digiprem.models

import empire.digiprem.models.database.User
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import java.util.*


@Entity
class UserReadMessage {
    @Id
    private val id: UUID = UUID.randomUUID()

    @ManyToOne(fetch = FetchType.LAZY)
    private var user: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    private var messages: Messages? = null
    private var readAt: LocalDateTime? = null
}