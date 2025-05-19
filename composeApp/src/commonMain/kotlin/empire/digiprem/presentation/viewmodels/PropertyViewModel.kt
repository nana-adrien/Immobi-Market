package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.components.app.RealEstateData
import empire.digiprem.presentation.components.app.generateFakeRealEstateListCameroon
import empire.digiprem.presentation.intents.PropertyIntent
import empire.digiprem.presentation.models.PropertyModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.views.RealEstateFilterData
import kotlinx.coroutines.flow.update

class PropertyViewModel : AbstractViewModel<PropertyModel,PropertyIntent>(defaultState = PropertyModel()) {
    // TODO: ViewModel logic
    init {
        _mutableState.update {
            it.copy(
                realEstates =  generateFakeRealEstateListCameroon()
            )
        }

    }

    override fun onIntentHandler(intent: PropertyIntent) {

    }
    private fun realEstateFilter(realEstateFilterData: RealEstateFilterData?): List<RealEstateData> {
        val realEstateDataList = generateFakeRealEstateListCameroon()
        return if (realEstateFilterData == null) realEstateDataList else realEstateDataList.filter { it.type == realEstateFilterData.type }
    }

}