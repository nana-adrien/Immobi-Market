package empire.digiprem.presentation.intents

import empire.digiprem.presentation.components.app.RealEstateType
import empire.digiprem.presentation.views.RealEstateFilterData

sealed class HomeIntent {
 object OnInit:HomeIntent()
 data class OnFilterRealEstates(val realEstateFilter: RealEstateFilterData?) : HomeIntent()
 data class OnFilterRealEstatesType(val type: RealEstateType) : HomeIntent()

 }