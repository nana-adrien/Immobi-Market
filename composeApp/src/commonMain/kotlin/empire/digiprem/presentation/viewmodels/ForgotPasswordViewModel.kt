package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.ForgotPasswordIntent
import empire.digiprem.presentation.models.ForgotPasswordModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class ForgotPasswordViewModel : AbstractViewModel<ForgotPasswordModel,ForgotPasswordIntent>(defaultState = ForgotPasswordModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: ForgotPasswordIntent) {
        TODO("Not yet implemented")
    }


}