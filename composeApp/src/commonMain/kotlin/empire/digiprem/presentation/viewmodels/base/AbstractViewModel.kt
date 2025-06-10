package empire.digiprem.presentation.viewmodels.base

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.util.fastJoinToString
import androidx.lifecycle.ViewModel
import empire.digiprem.data.config.BaseDataSourceEventHandler
import empire.digiprem.data.config.DataSourceEvent
import empire.digiprem.data.config.DataSourceEventCollector
import empire.digiprem.data.config.DataSourceEventHandlerDecorator
import empire.digiprem.model.ApiResponse2
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class AbstractViewModel<model, intent>(private val defaultState: model) : ViewModel() {

    protected val _mutableState = MutableStateFlow(defaultState)
    val state = _mutableState.asStateFlow()
    protected val _mutablePageWrapperState = MutableStateFlow(PageWrapperState())
    val pageWrapperState = _mutablePageWrapperState.asStateFlow()
    private val dataSourceCollector = DataSourceEventCollector()
    protected val defaultScope= CoroutineScope(Dispatchers.Default)
    protected val mainScope= CoroutineScope(Dispatchers.Main)
    init {
        _mutablePageWrapperState.update {
            it.copy(
                onRefresh = this::onRefreshPage
            )
        }

    }

    open fun onRefreshPage(){

    }

   private fun isSuccessDataSourceEvent(){
        _mutablePageWrapperState.update {
            it.copy(
                isSuccess = true,
                isLoading = false,
                errorMessage = ""
            )
        }
    }
    private fun isLoadingDataSourceEvent(){
        _mutablePageWrapperState.update {
            it.copy(
                isLoading = true,
                isSuccess = false
            )
        }
    }
    private fun isErrorDataSourceEvent(errorMessage:List<ApiResponse2.ErrorMessage>){
        _mutablePageWrapperState.update {
            it.copy(
                isSuccess = false,
                isLoading = false,
                isFailure = false,
                errorMessage = errorMessage.map{it.message}.joinToString(separator = "\n")
            )
        }
    }
    private fun isFaildDataSourceEvent(message: String) {
        _mutablePageWrapperState.update {
            it.copy(
                isSuccess = false,
                isLoading = false,
                isFailure = true,
                errorMessage =message
            )
        }
    }
    abstract fun onIntentHandler(intent: intent)
    suspend fun <T>collectDataSourceEvent(
        event: Flow<DataSourceEvent>,
        handler: DataSourceEventHandlerDecorator<T>
    ) {
        val locaHandler = object  :BaseDataSourceEventHandler<T>(){
            override suspend fun onLoading() {
                isLoadingDataSourceEvent()
            }
            override suspend fun onFailConnexion(message: String) {
                isFaildDataSourceEvent(message)
            }
            override suspend fun onErrorConnexion(code: Int, messages: List<ApiResponse2.ErrorMessage>) {
                isErrorDataSourceEvent(errorMessage=messages)
            }
            override suspend fun onFailProcess() {}
            override suspend fun onSuccessConnexion(key: Any?, data: T) { isSuccessDataSourceEvent() }
        }
        handler.setHandler(locaHandler)
        dataSourceCollector.collectEvent(event, handler)
    }

    fun onSuccess(data: model) {

    }
}