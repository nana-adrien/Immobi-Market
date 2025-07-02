package empire.digiprem.models.database

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
data class Image @OptIn(ExperimentalUuidApi::class) constructor(
    @Id val id: UUID = UUID.randomUUID(),
    @Column(columnDefinition = "text")
    val url: String,
    val isPrincipal: Boolean=false,
)
