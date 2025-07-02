package empire.digiprem.dto.bien_immobilier.get

import empire.digiprem.dto.bien_immobilier.CaracteristiqueOffreImmobilier
import empire.digiprem.enums.TypeDeBien
import empire.digiprem.enums.TypeDoffre
import empire.digiprem.models.database.Equipement

data class RecuperUneOffreResponseDTO (
    val id: String = "",
    val titre: String = "",
    val description: String = "",
    val superficie: Double = 0.0,
    val utilisateurId:String="",
    val listeImages: List<String> = emptyList(),
    val prix: Int = 0,
    val typeDeBien: TypeDeBien = TypeDeBien.NONE,
    val caracteristique: CaracteristiqueOffreImmobilier?=null,
    val typeDoffre: TypeDoffre = TypeDoffre.NONE,
    val dateCreation: String = "",
    val datePublication: String = "",
    val listEquipementId:List<Equipement> = emptyList(),
)