package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.components.app.RealEstateData
import empire.digiprem.presentation.components.app.RealEstateType
import empire.digiprem.presentation.components.app.generateFakeRealEstateListCameroon
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.intents.AllRealEstateIntent
import empire.digiprem.presentation.intents.HomeIntent
import empire.digiprem.presentation.models.AllRealEstateModel
import empire.digiprem.presentation.views.RealEstateFilterData
import kotlinx.coroutines.flow.update

class AllRealEstateViewModel : AbstractViewModel<AllRealEstateModel,AllRealEstateIntent>(defaultState = AllRealEstateModel()) {
    // TODO: ViewModel logic
    init {
        onIntentHandler( AllRealEstateIntent.OnFilterRealEstatesType(RealEstateType.ALL))
    }
        override fun onIntentHandler(intent: AllRealEstateIntent) {
            when (intent) {
                is AllRealEstateIntent.OnFilterRealEstates -> {
                    _mutableState.update {
                        it.copy(
                            realEstates = realEstateFilter(intent.realEstateFilter)
                        )
                    }
                }
                is AllRealEstateIntent.OnFilterRealEstatesType -> {
                    _mutableState.update {
                        it.copy(
                            realEstates = generateFakeRealEstateListCameroon().filter { if (intent.type == RealEstateType.ALL) true else it.type == intent.type }
                        )
                    }
                }
            }
         }

    private fun realEstateFilter(realEstateFilterData: RealEstateFilterData?): List<RealEstateData> {
        val realEstateDataList = generateFakeRealEstateListCameroon()
        return if (realEstateFilterData == null) realEstateDataList else realEstateDataList.filter { it.type == realEstateFilterData.type }
    }
}