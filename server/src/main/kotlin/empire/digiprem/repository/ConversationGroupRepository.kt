package empire.digiprem.repository

import empire.digiprem.models.ConversationGroup
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ConversationGroupRepository : JpaRepository<ConversationGroup?, UUID?>
