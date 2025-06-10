package empire.digiprem.data.config


import empire.digiprem.app.config.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

open class DataSourceEventCollector {
    suspend fun <T> collectEvent(
        event: Flow<DataSourceEvent>,
        handler: BaseDataSourceEventHandler<T>
    ) {
        try {
            event.collect { events ->
                dataSourceEventCollector(events, handler)
            }
        } catch (exception: Exception) {
            Log.e("Extract datasource error", "${exception.stackTraceToString()}")
            handler.onFailProcess()
        }
    }

    private suspend fun <T> dataSourceEventCollector(
        events: DataSourceEvent,
        handler: BaseDataSourceEventHandler<T>
    ) {
        when (events) {
            is DataSourceEvent.OnErrorDataSourceConnexionEvent -> {
                handler.onErrorConnexion(events.code, events.messages)
            }
            is DataSourceEvent.OnFailureDataSourceConnexionEvent -> {
                handler.onFailConnexion(events.message)
            }
            DataSourceEvent.OnLoadingDataSourceConnexionEvent -> {
                handler.onLoading()
            }
            is DataSourceEvent.OnSuccessDataSourceConnexionEvent<*> -> {
                handler.onSuccessConnexion(events.key, events.data as T)
            }
        }
    }

}