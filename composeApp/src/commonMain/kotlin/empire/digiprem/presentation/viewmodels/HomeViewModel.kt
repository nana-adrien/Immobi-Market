package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.components.app.RealEstateData
import empire.digiprem.presentation.components.app.generateFakeRealEstateListCameroon
import empire.digiprem.presentation.intents.HomeIntent
import empire.digiprem.presentation.models.HomeModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.views.RealEstateFilterData
import kotlinx.coroutines.flow.update

class HomeViewModel : AbstractViewModel<HomeModel,HomeIntent>(defaultState = HomeModel()) {

    override fun onIntentHandler(intent: HomeIntent) {
        when(intent) {
            is HomeIntent.OnFilterRealEstates ->{
                _mutableState.update {
                    it.copy(
                        realEstates = realEstateFilter(intent.realEstateFilter)
                    )
                }
            }
        }
    }

    private fun realEstateFilter(realEstateFilterData: RealEstateFilterData?): List<RealEstateData>{
        val realEstateDataList = generateFakeRealEstateListCameroon()
        return  if (realEstateFilterData==null) realEstateDataList else realEstateDataList.filter { it.type==realEstateFilterData.type }
    }

}