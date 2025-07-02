package empire.digiprem.models.database

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
data class Consultation @OptIn(ExperimentalUuidApi::class) constructor(
    @Id val id: UUID = UUID.randomUUID(),
    val dateDeConsultation: LocalDateTime,
    @ManyToOne
    @JoinColumn(name = "offre_id")
    val offre: OffreImmobiliere,
 //  val offre_id: Uuid = Uuid.random(),
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    val user: User,
  //  val app_user_id: Uuid = Uuid.random(),
)
