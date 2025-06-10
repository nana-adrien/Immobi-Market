package empire.digiprem.models

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne


@Entity
class ConversationPrivate : Conversation() {
    @ManyToOne(fetch = FetchType.LAZY)
    private var participant: Users? = null
}
