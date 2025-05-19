package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.intents.MobileDashBoardIntent
import empire.digiprem.presentation.models.MobileDashBoardModel
class MobileDashBoardViewModel : AbstractViewModel<MobileDashBoardModel,MobileDashBoardIntent>(defaultState = MobileDashBoardModel()) {
    // TODO: ViewModel logic

        override fun onIntentHandler(intent: MobileDashBoardIntent) {

         }
}