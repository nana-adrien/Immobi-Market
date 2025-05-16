package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.PropertyALLPropertyIntent
import empire.digiprem.presentation.models.PropertyALLPropertyModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class PropertyALLPropertyViewModel : AbstractViewModel<PropertyALLPropertyModel,PropertyALLPropertyIntent>(defaultState = PropertyALLPropertyModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: PropertyALLPropertyIntent) {
        TODO("Not yet implemented")
    }


}