package empire.digiprem.model

import empire.digiprem.enums.TypeDHabitation
import kotlinx.serialization.Serializable


@Serializable
abstract class CaracteristiqueOffreImmobilier {
    abstract val superficie: Double
}

@Serializable
abstract class Construction() : CaracteristiqueOffreImmobilier() {
    abstract val nombrePieces: Int
}

@Serializable
abstract class Habitation() : Construction() {
    abstract val typeDHabitation: TypeDHabitation
}

@Serializable
data class Chambre(
    override val nombrePieces: Int = 1,
    override val superficie: Double = 0.0,
    override val typeDHabitation: TypeDHabitation= TypeDHabitation.NONE,
) : Habitation()

@Serializable
data class Maison(
    override val superficie: Double = 0.0,
    val nombreChambre: Int=0,
    val nombreSalleDeBain: Int=1,
    val nombreCuisines: Int=1,
    override val typeDHabitation: TypeDHabitation= TypeDHabitation.NONE,
) : Habitation() {

    override val nombrePieces: Int
        get() = nombreCuisines + nombreChambre + nombreSalleDeBain
}
