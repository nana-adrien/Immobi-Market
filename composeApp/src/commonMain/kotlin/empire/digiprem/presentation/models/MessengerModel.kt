package empire.digiprem.presentation.models

import empire.digiprem.model.chat.Conversation

data class MessengerModel(
    val isLoading: Boolean = false,
    val searchText: String = "",
    val conversations: List<Conversation> = listOf(),
    val currentSelectConversation: Conversation? = null
)