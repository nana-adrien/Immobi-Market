package empire.digiprem.model.chat

import empire.digiprem.enums.chat.ConversationTypeEnum
import empire.digiprem.enums.chat.MessageStatusEnum
import kotlinx.serialization.Serializable

@Serializable
data class Conversation(
    val id: Int = 0,
    val selected: Boolean = false,
    val online: Boolean = false,
    val title: String = "",
    val sender: String = "",
    val lastSeen: String? = null,                    // Date/heure de dernière activité de l'autre utilisateur
    val unreadCount: Int = 0,                        // Nombre de messages non lus
    val typing: Boolean = false,                     // L'autre utilisateur est en train d’écrire
    val pinned: Boolean = false,                     // Conversation épinglée
    val muted: Boolean = false,                      // Notifications désactivées
    val archived: Boolean = false,                   // Conversation archivée
    val blocked: Boolean = false,                    // Utilisateur bloqué
    val favorite: Boolean = false,                   // Conversation marquée comme favorite
    val conversationType: ConversationTypeEnum,
    val status: MessageStatusEnum,
    val timeIndicator: String,
    val messages: MutableList<Message> = mutableListOf(),   // Historique des messages
)