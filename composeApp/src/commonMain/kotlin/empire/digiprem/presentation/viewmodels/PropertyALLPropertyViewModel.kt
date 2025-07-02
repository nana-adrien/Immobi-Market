package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.components.app.RealEstateData
import empire.digiprem.presentation.components.app.generateFakeRealEstateListCameroon
import empire.digiprem.presentation.intents.PropertyALLPropertyIntent
import empire.digiprem.presentation.intents.PropertyIntent
import empire.digiprem.presentation.models.PropertyALLPropertyModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.views.RealEstateFilterData
import kotlinx.coroutines.flow.update

class PropertyALLPropertyViewModel : AbstractViewModel<PropertyALLPropertyModel,PropertyALLPropertyIntent>(defaultState = PropertyALLPropertyModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: PropertyALLPropertyIntent) {
        TODO("Not yet implemented")
    }
    init {
        _mutableState.update {
            it.copy(
                realEstates =  generateFakeRealEstateListCameroon()
            )
        }
    }
    private fun realEstateFilter(realEstateFilterData: RealEstateFilterData?): List<RealEstateData> {
        val realEstateDataList = generateFakeRealEstateListCameroon()
        return if (realEstateFilterData == null) realEstateDataList else realEstateDataList.filter { it.type == realEstateFilterData.type }
    }
}