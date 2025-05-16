package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.VerifyIdentityIntent
import empire.digiprem.presentation.models.VerifyIdentityModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class VerifyIdentityViewModel : AbstractViewModel<VerifyIdentityModel,VerifyIdentityIntent>(defaultState = VerifyIdentityModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: VerifyIdentityIntent) {
        TODO("Not yet implemented")
    }


}