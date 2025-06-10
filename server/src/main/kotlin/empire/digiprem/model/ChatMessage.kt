package empire.digiprem.model

class ChatMessage {
    var type: MessageType? = null
    var content: String? = null
    var sender: String? = null

    constructor()

    constructor(type: MessageType?, content: String?, sender: String?) {
        this.type = type
        this.content = content
        this.sender = sender
    }

    class Builder {
        private var type: MessageType? = null
        private var content: String? = null
        private var sender: String? = null

        fun setType(type: MessageType?): Builder {
            this.type = type
            return this
        }

        fun setContent(content: String?): Builder {
            this.content = content
            return this
        }

        fun setSender(sender: String?): Builder {
            this.sender = sender
            return this
        }

        fun build(): ChatMessage {
            return ChatMessage(type, content, sender)
        }
    }
}