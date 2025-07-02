package empire.digiprem.data.remote.dto.oauth

import empire.digiprem.enums.TypeDHabitation
import empire.digiprem.enums.TypeDeBien
import empire.digiprem.enums.TypeDoffre
import empire.digiprem.presentation.components.app.Equipment
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetRealEstateResponse(
    val id: String,
    val titre: String,
    val description: String,
    val superficie: Double,
    val utilisateurId: String,
    val listeImages: List<String>,
    val prix: Long,
    val typeDeBien: TypeDeBien,
    val caracteristique: Caracteristique,
    val typeDoffre: TypeDoffre,
    val dateCreation: String,
    val datePublication: String,
    val listEquipementId: List<Equipement>
)
@Serializable
@SerialName("MAISON")
data class CaracteristiqueMaison(
    override val superficie: Double=0.0,
  //  override val typeDeBien: String="",
    override val nombrePieces: Int=0,
    override val typeDHabitation: TypeDHabitation=TypeDHabitation.NONE,
    val nombreChambre: Int=0,
    val nombreSalleDeBain: Int=1,
    val nombreCuisines: Int=1,
):Caracteristique()

@Serializable
@SerialName("CHAMBRE")
data class CaracteristiqueChambre(
    override val superficie: Double=0.0,
  //  override val typeDeBien: String="",
    override val nombrePieces: Int=0,
    override val typeDHabitation: TypeDHabitation=TypeDHabitation.NONE,
    val nombreChambre: Int=0,
    val nombreSalleDeBain: Int=1,
    val nombreCuisines: Int=1,
):Caracteristique()


@Serializable
@SerialName("Caracteristiques")
@Polymorphic
sealed class Caracteristique{
   abstract val superficie: Double
   // abstract   val typeDeBien: String
    abstract val nombrePieces: Int
    abstract val typeDHabitation: TypeDHabitation
}

@Serializable
data class Equipement(
    val id: String,
    val nom: String,
)