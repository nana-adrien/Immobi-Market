package empire.digiprem.presentation.intents

sealed class VerifyIdentityIntent {
 data class OnSendForm(val email: String, val operationType: String):VerifyIdentityIntent()
 data class OnPinCodeChange(val pincode: List<String>): VerifyIdentityIntent()
 }