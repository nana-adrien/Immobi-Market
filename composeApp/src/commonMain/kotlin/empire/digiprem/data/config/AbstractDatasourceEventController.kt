package empire.digiprem.data.config


import empire.digiprem.model.ApiResponse2
import io.ktor.client.network.sockets.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import empire.digiprem.app.config.Log

abstract class AbstractDatasourceEventController{
    /*
    protected inline fun <reified T> dataSourceEventControllerEx22(
        key: Any? = null,
        crossinline request: suspend () -> HttpResponse
    ): Flow<DataSourceEvent> = flow {
        emit(DataSourceEvent.OnLoadingDataSourceConnexionEvent)
        val result = request()
        if (result.status.value>=200) {
            result.body<T>()?.let{
                emit(DataSourceEvent.OnSuccessDataSourceConnexionEvent(key = key, data = it as T))
            } ?:emit(DataSourceEvent.OnErrorDataSourceConnexionEvent(messages = listOf("errorMessages=body null") ))
        } else {
            if (result.status.value >= 500){
                emit(
                    DataSourceEvent.OnFailureDataSourceConnexionEvent(
                        result.body()
                    )
                )
                println("${result.body<Any>()}")
            } else {
                //emit(DataSourceEvent.OnErrorDataSourceConnexionEvent(code =result.status.value ,listOf(result.message)))
            }

        }
    }.catch { failMessage ->
        Log.e("RoutesAPIDataSource", failMessage.stackTraceToString())
        emit(DataSourceEvent.OnFailureDataSourceConnexionEvent("erreur grave c'est produit"))
    }
    protected  fun  <T> dataSourceEventControllerEx2(
        key: Any? = null,
         request: suspend () -> ApiResponse<T>
    ): Flow<DataSourceEvent> = flow {
        emit(DataSourceEvent.OnLoadingDataSourceConnexionEvent)
        val result = request()
        if (result.hasSuccess()) {
            result.body()?.let{
                emit(DataSourceEvent.OnSuccessDataSourceConnexionEvent(key = key, data = it as T))
            } ?:emit(DataSourceEvent.OnErrorDataSourceConnexionEvent(messages = listOf(result.message()?:"") ))
        } else {
            if (result.status() >= 500){
                emit(
                    DataSourceEvent.OnFailureDataSourceConnexionEvent(result.message()?:"")
                )
                println("${result.body()}")
            } else {
                emit(DataSourceEvent.OnErrorDataSourceConnexionEvent(code =result.status() ,listOf(result.message()?:"")))
            }

        }
    }.catch { failMessage ->
        Log.e("RoutesAPIDataSource", failMessage.stackTraceToString())
        emit(DataSourceEvent.OnFailureDataSourceConnexionEvent("erreur grave c'est produit"))
    }*/
    protected  fun  <T> dataSourceEventControllerEx(
        key: Any? = null,
         request: suspend () -> ApiResponse2<T>
    ): Flow<DataSourceEvent> = flow {
        emit(DataSourceEvent.OnLoadingDataSourceConnexionEvent)
        val result = request()
        if (result.success) {
            result.payload?.let{
                emit(DataSourceEvent.OnSuccessDataSourceConnexionEvent(key = key, data = it as T))
            } ?:emit(DataSourceEvent.OnErrorDataSourceConnexionEvent(messages =result.errorMessages))
        } else {
            if (result.errorMessages.first().field.equals("throwable")){
                emit(DataSourceEvent.OnFailureDataSourceConnexionEvent(result.errorMessages.first().message))
               // throw  Exception()
            } else {
                emit(DataSourceEvent.OnErrorDataSourceConnexionEvent(messages =result.errorMessages))
            }

        }
    }.catch { failMessage ->
        Log.e("RoutesAPIDataSource", failMessage.stackTraceToString())
        emit(DataSourceEvent.OnFailureDataSourceConnexionEvent("erreur grave c'est produit"))
    }
}




 fun<T> Flow<DataSourceEvent>.parseSuccessDataSource(
    key: Any?=null,
    parseTo: (Any?) ->T,
): Flow<DataSourceEvent> = flow{
    collect{
       when(it){
           is DataSourceEvent.OnSuccessDataSourceConnexionEvent<*>->{
               emit(DataSourceEvent.OnSuccessDataSourceConnexionEvent(key =key?:it.key, data =parseTo(it.data)))
           }
           else ->emit(it)
       }
    }
}
