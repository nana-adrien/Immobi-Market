package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.intents.ClaimsIntent
import empire.digiprem.presentation.models.ClaimsModel
class ClaimsViewModel : AbstractViewModel<ClaimsModel,ClaimsIntent>(defaultState = ClaimsModel()) {
    // TODO: ViewModel logic

        override fun onIntentHandler(intent: ClaimsIntent) {

         }
}