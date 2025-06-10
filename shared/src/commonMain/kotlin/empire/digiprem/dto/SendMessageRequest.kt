package empire.digiprem.dto

import empire.digiprem.model.MessageType

class SendMessageRequest(
    type: MessageType,
    val content: String
) {
    val type: MessageType = type
}
