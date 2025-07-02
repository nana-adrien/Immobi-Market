package empire.digiprem.dto.bien_immobilier

import empire.digiprem.models.database.Equipement

data class GetBienDetailResponseDTO (
    val caracteristiques: CaracteristiqueOffreImmobilier?=null,
    val equipements: List<Equipement> = listOf()
)