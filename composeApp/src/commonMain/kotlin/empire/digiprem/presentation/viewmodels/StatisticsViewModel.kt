package empire.digiprem.presentation.viewmodels
import empire.digiprem.presentation.intents.StatisticsIntent
import empire.digiprem.presentation.models.StatisticsModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel

class StatisticsViewModel : AbstractViewModel<StatisticsModel,StatisticsIntent>(defaultState = StatisticsModel()) {
    // TODO: ViewModel logic
    override fun onIntentHandler(intent: StatisticsIntent) {
        TODO("Not yet implemented")
    }


}