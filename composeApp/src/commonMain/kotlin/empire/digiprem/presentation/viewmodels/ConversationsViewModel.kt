package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.ConversationsIntent
import empire.digiprem.presentation.models.ConversationsModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class ConversationsViewModel : AbstractViewModel<ConversationsModel,ConversationsIntent>(defaultState = ConversationsModel()) {
    // TODO: ViewModel logic

    override fun onIntentHandler(intent: ConversationsIntent) {
        TODO("Not yet implemented")
    }

    override fun onRefreshPage() {
        TODO("Not yet implemented")
    }
}