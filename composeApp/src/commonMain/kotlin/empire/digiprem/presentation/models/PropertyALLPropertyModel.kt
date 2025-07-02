package empire.digiprem.presentation.models

import empire.digiprem.presentation.components.app.RealEstateData

data class PropertyALLPropertyModel(
    val isLoading: Boolean = false,
    val realEstates: List<RealEstateData> = emptyList()
)