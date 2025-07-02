package empire.digiprem.presentation.intents

sealed class LoginIntent {
 object OnSendForm:LoginIntent()
 data class onChangeTextField(val key:String, val value:String):LoginIntent()
 }