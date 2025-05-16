package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.NegotiationsIntent
import empire.digiprem.presentation.models.NegotiationsModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class NegotiationsViewModel : AbstractViewModel<NegotiationsModel,NegotiationsIntent>(defaultState = NegotiationsModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: NegotiationsIntent) {
        TODO("Not yet implemented")
    }

}