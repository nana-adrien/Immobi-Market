package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.RegisterIntent
import empire.digiprem.presentation.models.RegisterModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class RegisterViewModel : AbstractViewModel<RegisterModel,RegisterIntent>(defaultState = RegisterModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: RegisterIntent) {
        TODO("Not yet implemented")
    }


}