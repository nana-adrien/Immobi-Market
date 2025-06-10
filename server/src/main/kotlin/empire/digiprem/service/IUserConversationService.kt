package empire.digiprem.service

import empire.digiprem.models.Conversation

interface IUserConversationService {
    fun getUserConversations(userId: String): Collection<Conversation>?
}