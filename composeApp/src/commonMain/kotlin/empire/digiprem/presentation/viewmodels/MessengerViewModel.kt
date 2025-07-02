package empire.digiprem.presentation.viewmodels
import androidx.lifecycle.viewModelScope
import empire.digiprem.data.local.DataBaseTemp
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.intents.MessengerIntent
import empire.digiprem.presentation.models.MessengerModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MessengerViewModel : AbstractViewModel<MessengerModel,MessengerIntent>(defaultState = MessengerModel()) {
    // TODO: ViewModel logic
    init {
        onIntentHandler(MessengerIntent.InitialIntent)
    }

        override fun onIntentHandler(intent: MessengerIntent) {
            viewModelScope.launch {
                when (intent) {
                    MessengerIntent.InitialIntent -> {
                        _mutableState.update {
                            it.copy(
                                conversations = DataBaseTemp.testConversations,
                                currentSelectConversation = null)
                        }
                    }
                    is MessengerIntent.OnConversationItemClicked -> {
                        _mutableState.update {
                            it.copy(
                                currentSelectConversation = intent.conversation
                            )
                        }
                    }
                    is MessengerIntent.OnSendMessage -> {
                        val conversationsTemp= _mutableState.value.conversations.toMutableList()
                        conversationsTemp.first{it.id==intent.conversationId}.messages.add(intent.message)
                        _mutableState.update {
                            it.copy(
                                conversations =conversationsTemp,
                                currentSelectConversation =conversationsTemp.first{it.id==intent.conversationId}
                            )
                        }
                    }

                    is MessengerIntent.OnSearch -> {
                        _mutableState.update {
                            it.copy(
                                searchText = intent.text
                            )
                        }
                    }
                }
            }
         }
}