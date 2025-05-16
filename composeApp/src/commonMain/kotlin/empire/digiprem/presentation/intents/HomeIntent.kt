package empire.digiprem.presentation.intents

import empire.digiprem.presentation.views.RealEstateFilterData

sealed class HomeIntent {
 data class OnFilterRealEstates(val realEstateFilter: RealEstateFilterData?) : HomeIntent()

 }