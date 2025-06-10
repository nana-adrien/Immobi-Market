package empire.digiprem.presentation.intents

import empire.digiprem.presentation.viewmodels.RegisterViewModel

sealed class RegisterIntent {

 object OnSendForm:RegisterIntent()
 data class onChangeTextField(val key:String,val value:String):RegisterIntent()

 }