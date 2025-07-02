package empire.digiprem.presentation.intents

import empire.digiprem.presentation.components.app.RealEstateType
import empire.digiprem.presentation.views.RealEstateFilterData

sealed class AllRealEstateIntent {
 data class OnFilterRealEstates(val realEstateFilter: RealEstateFilterData?) : AllRealEstateIntent()
 data class OnFilterRealEstatesType(val type: RealEstateType) : AllRealEstateIntent()

 }