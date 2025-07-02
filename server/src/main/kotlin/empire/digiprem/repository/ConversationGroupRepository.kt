package empire.digiprem.repository

import empire.digiprem.models.ConversationsGroup
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ConversationGroupRepository : JpaRepository<ConversationsGroup?, UUID?>
