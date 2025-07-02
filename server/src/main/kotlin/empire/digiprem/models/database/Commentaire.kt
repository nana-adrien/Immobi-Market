package empire.digiprem.models.database

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
data class Commentaire @OptIn(ExperimentalUuidApi::class) constructor(
    @Id val id: UUID = UUID.randomUUID(),
    val note: Int,
    val contenu: String,
    val datePublication: LocalDateTime,
    @ManyToOne
    @JoinColumn(name = "offre_id", nullable = false)
    val offre: OffreImmobiliere,
 //   val offre_id :Uuid=Uuid.random(),
    @ManyToOne
    @JoinColumn(name = "app_user_id", nullable = false)
    val user: User,
   // val app_user_id :Uuid=Uuid.random(),
)
