package empire.digiprem.repository

import empire.digiprem.models.UserParticipatesConversation
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserParticipatesConversationRepository : JpaRepository<UserParticipatesConversation?, UUID?>
