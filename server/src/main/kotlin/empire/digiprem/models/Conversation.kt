package empire.digiprem.models

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: UUID? = null
    private var name: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    private var creatorId: Users? = null
    private var creationDate: LocalDateTime? = null
}
