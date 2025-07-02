@file:OptIn(ExperimentalUuidApi::class)

package empire.digiprem.models.database

import empire.digiprem.enums.TypeDeBien
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
data class Equipement @OptIn(ExperimentalUuidApi::class) constructor(
    @Id val id: UUID = UUID.randomUUID(),
    val nom: String,
    val typeDeBienAssocier:List<TypeDeBien>
)



