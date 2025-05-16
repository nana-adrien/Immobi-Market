package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.intents.SplashIntent
import empire.digiprem.presentation.models.SplashModel
class SplashViewModel : AbstractViewModel<SplashModel,SplashIntent>(defaultState = SplashModel()) {
    // TODO: ViewModel logic

        override fun onIntentHandler(intent: SplashIntent) {

         }
}