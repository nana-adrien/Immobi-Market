package empire.digiprem.dto.bien_immobilier

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import empire.digiprem.enums.TypeDHabitation

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY, // 👈 clé déjà existante
    property = "typeDeBien",
    visible = true // 👈 permet de garder le champ dans l'objet final
)
@JsonSubTypes(
    JsonSubTypes.Type(value = Maison::class, name = "MAISON"),
    JsonSubTypes.Type(value = Chambre::class, name = "CHAMBRE")
)
abstract class CaracteristiqueOffreImmobilier {
    abstract val typeDeBien: String
    abstract val superficie: Double
}

abstract class Construction() : CaracteristiqueOffreImmobilier() {
    abstract val nombrePieces: Int
}

abstract class Habitation() : Construction() {
    abstract val typeDHabitation: TypeDHabitation
}

@JsonTypeName("CHAMBRE")
data class Chambre(
    override val typeDeBien: String = "CHAMBRE",
    override val nombrePieces: Int = 1,
    override val superficie: Double = 0.0,
    override val typeDHabitation: TypeDHabitation= TypeDHabitation.NONE,
) : Habitation()

@JsonTypeName("MAISON")
data class Maison(
    override val typeDeBien: String = "MAISON",
    override val superficie: Double = 0.0,
    val nombreChambre: Int=0,
    val nombreSalleDeBain: Int=1,
    val nombreCuisines: Int=1,
    override val typeDHabitation: TypeDHabitation= TypeDHabitation.NONE,
) : Habitation() {

    override val nombrePieces: Int
        get() = nombreCuisines + nombreChambre + nombreSalleDeBain
}
