package empire.digiprem.presentation.models

import empire.digiprem.presentation.components.app.RealEstateData

data class PropertyModel(
    val isLoading: Boolean = false,
    val realEstates: List<RealEstateData> = emptyList()
)