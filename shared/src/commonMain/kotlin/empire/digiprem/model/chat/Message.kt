package empire.digiprem.model.chat

import empire.digiprem.enums.chat.MessageTypeEnum
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val id: String = "",
    val userName: String = "",
    val content: String = "",
    val imageUrl: String? = null,
    val replyTo: Message? = null,
    val isSender: Boolean = false,
    val messageType: MessageTypeEnum = MessageTypeEnum.TEXT,
    val timestamp: String = "",                     // Date/heure d'envoi
    val seen: Boolean = false,                      // Vu par le destinataire
    val delivered: Boolean = false,                 // Livré au destinataire
    val edited: Boolean = false,                    // Message modifié
    val deleted: Boolean = false,                   // Message supprimé
    val attachments: List<String> = emptyList(),    // Liste d’URLs ou chemins vers des fichiers joints
)
