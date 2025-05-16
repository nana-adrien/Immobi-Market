package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.LoginIntent
import empire.digiprem.presentation.models.LoginModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class LoginViewModel : AbstractViewModel<LoginModel,LoginIntent>(defaultState = LoginModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: LoginIntent) {
        TODO("Not yet implemented")
    }

}