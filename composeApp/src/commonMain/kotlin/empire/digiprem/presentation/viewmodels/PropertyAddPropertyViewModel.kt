package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.PropertyAddPropertyIntent
import empire.digiprem.presentation.models.PropertyAddPropertyModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class PropertyAddPropertyViewModel : AbstractViewModel<PropertyAddPropertyModel,PropertyAddPropertyIntent>(defaultState = PropertyAddPropertyModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: PropertyAddPropertyIntent) {
        TODO("Not yet implemented")
    }


}