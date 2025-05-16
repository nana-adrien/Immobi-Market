package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.PropertyIntent
import empire.digiprem.presentation.models.PropertyModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class PropertyViewModel : AbstractViewModel<PropertyModel,PropertyIntent>(defaultState = PropertyModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: PropertyIntent) {
        TODO("Not yet implemented")
    }


}