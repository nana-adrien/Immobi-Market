package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.ChatIntent
import empire.digiprem.presentation.models.ChatModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class ChatViewModel : AbstractViewModel<ChatModel,ChatIntent>(defaultState = ChatModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: ChatIntent) {
        TODO("Not yet implemented")
    }

    override fun onRefreshPage() {
        TODO("Not yet implemented")
    }
}