package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.ResetPasswordIntent
import empire.digiprem.presentation.models.ResetPasswordModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class ResetPasswordViewModel : AbstractViewModel<ResetPasswordModel,ResetPasswordIntent>(defaultState = ResetPasswordModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: ResetPasswordIntent) {
        TODO("Not yet implemented")
    }


}