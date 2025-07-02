@file:OptIn(ExperimentalUuidApi::class)

package empire.digiprem.models.database.caracteristique

import empire.digiprem.enums.TypeDHabitation
import empire.digiprem.utils.convertor.TypeDHabitationConverter
import jakarta.persistence.*
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
@Table(name = "offre_carateristiques")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Caracteristiques {
   @Id
   val id: UUID = UUID.randomUUID()
    abstract val superficie: Double
}

@Entity
@Table(name = "carateristiques_offre_terrain")
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Terrain:Caracteristiques() {
    abstract val terrainTitre: Boolean

}

@Entity
@Table(name = "carateristiques_offre_Construction")
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Construction:Caracteristiques() {
    abstract val nombreDePieces: Int
}

@Entity
@Table(name = "carateristiques_offre_Construction_Habitation")
@PrimaryKeyJoinColumn(name = "id")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Habitation:Construction() {
    @get:Convert(converter = TypeDHabitationConverter::class)
    abstract val typeDHabitation: TypeDHabitation
}
