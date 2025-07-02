package empire.digiprem.presentation.intents

import empire.digiprem.model.chat.Conversation
import empire.digiprem.model.chat.Message

sealed class MessengerIntent {

 object InitialIntent : MessengerIntent()
 data class OnSearch(val text: String) : MessengerIntent()
 data class OnConversationItemClicked(val conversation: Conversation) : MessengerIntent()
 data class OnSendMessage( val conversationId:Int, val message: Message) : MessengerIntent()
 }