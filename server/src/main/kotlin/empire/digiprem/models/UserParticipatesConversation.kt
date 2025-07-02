package empire.digiprem.models

import empire.digiprem.models.database.User
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import java.util.*


@Entity
class UserParticipatesConversation {
    @Id
    private val id: UUID = UUID.randomUUID()

    @ManyToOne(fetch = FetchType.LAZY)
    private var participate: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    private var conversation: ConversationsGroup? = null
    private var addedAt: LocalDateTime? = null
    private var leaveAt: LocalDateTime? = null
    private var role: RoleEnum? = null
    private var status: StatutEnum? = null

    @ManyToOne(fetch = FetchType.LAZY)
    private var excludedByUser: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    private var addedByUser: User? = null

    enum class RoleEnum {
        ADMIN,
        MEMBER
    }

    enum class StatutEnum {
        ADDED,
        LEAVED,
        EXCLUDED
    }
}
