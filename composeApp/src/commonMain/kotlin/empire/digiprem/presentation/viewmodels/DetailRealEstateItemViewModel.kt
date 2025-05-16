package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.DetailRealEstateItemIntent
import empire.digiprem.presentation.models.DetailRealEstateItemModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class DetailRealEstateItemViewModel : AbstractViewModel<DetailRealEstateItemModel,DetailRealEstateItemIntent>(defaultState = DetailRealEstateItemModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: DetailRealEstateItemIntent) {
        TODO("Not yet implemented")
    }


}