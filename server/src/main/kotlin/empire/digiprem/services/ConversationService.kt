package empire.digiprem.services

import empire.digiprem.models.Conversations
import empire.digiprem.service.IUserConversationService
import org.springframework.stereotype.Service


@Service
class ConversationService : IUserConversationService {
    override fun getUserConversations(userId: String): Collection<Conversations>? {
         return listOf()
    }
}
