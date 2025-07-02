package empire.digiprem.models.database

import jakarta.persistence.*
import java.time.Instant
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
data class Message @OptIn(ExperimentalUuidApi::class) constructor(
    @Id val id: UUID = UUID.randomUUID(),
    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    val conversation: Conversation,
  //  val conversation_id: UUID = UUID.randomUUID(),
    @ManyToOne
    @JoinColumn(name = "emetteur_id", nullable = false)
    val emetteur: Conversation,
   // val emetteur_id: UUID = UUID.randomUUID(),
    val contenu: String,
    val image: String?,
    val recu: Boolean,
    @OneToOne
    @JoinColumn(name = "principal_message_id", nullable = false)
    val principalMessage: Message?=null,
 //   val principal_message_id: UUID = UUID.randomUUID(),
    val consulte: Boolean,
    val dateEnvoi: Instant,
    val dateReception: Instant
)
