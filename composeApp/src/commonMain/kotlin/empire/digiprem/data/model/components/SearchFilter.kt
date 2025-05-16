package empire.digiprem.data.model.components

import empire.digiprem.presentation.components.app.Equipment
import empire.digiprem.presentation.components.app.RealEstateCategories
import empire.digiprem.presentation.components.app.RealEstateType
import kotlinx.datetime.LocalDate

data class SearchFilter(
    val type: RealEstateType?,
    val category: RealEstateCategories?,
    val equipments: List<Equipment>,
    val minPrice: Int?,
    val maxPrice: Int?,
    val region: String?,
    val city: String?,
    val district: String?,
    val date: LocalDate?
)
