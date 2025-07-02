package empire.digiprem.models.database

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
data class Favori @OptIn(ExperimentalUuidApi::class) constructor(
    @Id val id: UUID = UUID.randomUUID(),
    val dateAjout: LocalDateTime,
    @ManyToOne
    @JoinColumn(name = "offre_id", nullable = false)
    val offre: OffreImmobiliere,
//    val offre_id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,
   // val user_id: UUID = UUID.randomUUID(),

    )
