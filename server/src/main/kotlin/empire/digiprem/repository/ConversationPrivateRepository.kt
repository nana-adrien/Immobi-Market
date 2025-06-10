package empire.digiprem.repository

import empire.digiprem.models.ConversationPrivate
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ConversationPrivateRepository : JpaRepository<ConversationPrivate?, UUID?> {
    fun findByParticipantId(participantId: UUID?): Optional<ConversationPrivate?>?
}