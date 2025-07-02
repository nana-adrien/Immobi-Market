package empire.digiprem.dto.bien_immobilier

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class PublierUneOffreReqDTO(
    val idOffre: String = "",
    val dateExpiration: String =  Clock.System.now().toString(),
)