package empire.digiprem.models

import empire.digiprem.models.database.User
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne


@Entity
class ConversationsPrivate : Conversations() {
    @ManyToOne(fetch = FetchType.LAZY)
    private var participant: User? = null
}
