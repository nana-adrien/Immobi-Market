package empire.digiprem.service

import empire.digiprem.models.Conversations

interface IUserConversationService {
    fun getUserConversations(userId: String): Collection<Conversations>?
}