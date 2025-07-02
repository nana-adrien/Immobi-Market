package empire.digiprem.presentation.models

import empire.digiprem.presentation.components.app.RealEstateData

data class AllRealEstateModel(
    val isLoading: Boolean = false,
    val realEstates:List<RealEstateData> =emptyList(),
)