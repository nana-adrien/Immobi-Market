package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.ProfilIntent
import empire.digiprem.presentation.models.ProfilModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class ProfilViewModel : AbstractViewModel<ProfilModel,ProfilIntent>(defaultState = ProfilModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: ProfilIntent) {
        TODO("Not yet implemented")
    }


}