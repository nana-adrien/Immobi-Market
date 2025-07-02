package empire.digiprem.dto.bien_immobilier.create

import empire.digiprem.dto.bien_immobilier.CaracteristiqueOffreImmobilier
import empire.digiprem.enums.TypeDeBien
import empire.digiprem.enums.TypeDoffre

data class EnregisterUneOffreReqDTO(
    val titre: String = "",
    val description: String = "",
    val superficie: Double,
    val listeImages:List<String>,
    val prix: Int,
    val typeDeBien: TypeDeBien = TypeDeBien.NONE,
    //@JsonDeserialize(using = CaracteristiqueOffreImmobilierDeserializer::class)
    val caracteristique: CaracteristiqueOffreImmobilier? = null,
    val listEquipementId: List<String> = emptyList(),
    val typeDoffre: TypeDoffre = TypeDoffre.NONE,
)