package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.SettingsIntent
import empire.digiprem.presentation.models.SettingsModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class SettingsViewModel : AbstractViewModel<SettingsModel,SettingsIntent>(defaultState = SettingsModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: SettingsIntent) {
        TODO("Not yet implemented")
    }


}