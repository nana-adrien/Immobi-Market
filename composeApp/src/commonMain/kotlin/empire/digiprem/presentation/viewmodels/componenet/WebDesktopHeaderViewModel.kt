package empire.digiprem.presentation.viewmodels.componenet

import androidx.lifecycle.viewModelScope
import empire.digiprem.data.local.DataBaseTemp
import empire.digiprem.presentation.intents.component.WebDesktopHeaderIntent
import empire.digiprem.presentation.models.components.WebDesktopHeaderModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class WebDesktopHeaderViewModel(val isConnected:Boolean): AbstractViewModel<WebDesktopHeaderModel, WebDesktopHeaderIntent>(defaultState = WebDesktopHeaderModel()){
    override fun onIntentHandler(intent: WebDesktopHeaderIntent) {
      viewModelScope.launch {
          when (intent) {
              WebDesktopHeaderIntent.OnInitIntent -> {
                 getNotifications(isConnected)
              }
          }
      }
    }
    suspend fun getNotifications(enabled:Boolean){
        if(enabled){
            while (true){
                _mutableState.update {
                    it.copy(
                        notifications = DataBaseTemp.notifications.filter { n->/*it.id<= Random.nextInt(6) || */n.isRead==Random.nextBoolean() },
                        messages = listOf(

                        )
                    )
                }
                delay(1*60*1000L)
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
      viewModelScope.launch {
          getNotifications(false)
      }
    }
}