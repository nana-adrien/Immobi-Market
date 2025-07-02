package empire.digiprem.presentation.models.components

import empire.digiprem.model.chat.Conversation
import empire.digiprem.presentation.views.NotificationItem

data class WebDesktopHeaderModel(
    val notifications:List<NotificationItem> = emptyList(),
    val messages: List<Conversation> = emptyList(),
)