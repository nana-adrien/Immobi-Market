package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.intents.RequestsValidationIntent
import empire.digiprem.presentation.models.RequestsValidationModel
class RequestsValidationViewModel : AbstractViewModel<RequestsValidationModel,RequestsValidationIntent>(defaultState = RequestsValidationModel()) {
    // TODO: ViewModel logic

        override fun onIntentHandler(intent: RequestsValidationIntent) {

         }
}