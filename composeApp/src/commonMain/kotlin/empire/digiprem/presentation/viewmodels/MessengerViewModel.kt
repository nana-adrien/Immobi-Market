package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.intents.MessengerIntent
import empire.digiprem.presentation.models.MessengerModel
class MessengerViewModel : AbstractViewModel<MessengerModel,MessengerIntent>(defaultState = MessengerModel()) {
    // TODO: ViewModel logic

        override fun onIntentHandler(intent: MessengerIntent) {

         }
}