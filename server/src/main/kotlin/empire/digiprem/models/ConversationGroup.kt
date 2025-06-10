package empire.digiprem.models

import jakarta.persistence.Entity

@Entity
class ConversationGroup : Conversation() {
    private var description: String = ""
    private var photoUrl: String = ""
}