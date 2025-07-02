package empire.digiprem.models

import jakarta.persistence.Entity

@Entity
class ConversationsGroup : Conversations() {
    private var description: String = ""
    private var photoUrl: String = ""
}