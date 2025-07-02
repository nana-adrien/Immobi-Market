package empire.digiprem.models

import empire.digiprem.models.database.User
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Conversations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: UUID? = null
    private var name: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    private var creatorId: User? = null
    private var creationDate: LocalDateTime? = null
}
