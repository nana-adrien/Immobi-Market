package empire.digiprem.services

import empire.digiprem.models.Conversation
import empire.digiprem.service.IUserConversationService
import org.springframework.stereotype.Service


@Service
class ConversationService : IUserConversationService {
    override fun getUserConversations(userId: String): Collection<Conversation>? {
         return listOf()
    }
}
