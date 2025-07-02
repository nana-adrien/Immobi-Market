package empire.digiprem.models.database

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
data class Conversation @OptIn(ExperimentalUuidApi::class) constructor(
    @Id val id: Uuid = Uuid.random(),

    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    val proprietaire: User,
  //  val participant_id: Uuid = Uuid.random(),
    @ManyToOne
    @JoinColumn(name = "createur_id", nullable = false)
    val demandeur: User,
   // val createur_id: Uuid = Uuid.random(),
    val dateDeCreation: LocalDateTime
)
