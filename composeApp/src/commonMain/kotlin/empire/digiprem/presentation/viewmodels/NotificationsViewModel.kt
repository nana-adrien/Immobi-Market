package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.NotificationsIntent
import empire.digiprem.presentation.models.NotificationsModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class NotificationsViewModel : AbstractViewModel<NotificationsModel,NotificationsIntent>(defaultState = NotificationsModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: NotificationsIntent) {
        TODO("Not yet implemented")
    }


}