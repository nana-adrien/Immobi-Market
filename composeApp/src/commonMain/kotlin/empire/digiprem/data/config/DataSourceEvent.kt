package empire.digiprem.data.config

import empire.digiprem.model.ApiResponse2

sealed class DataSourceEvent {
    object OnLoadingDataSourceConnexionEvent:DataSourceEvent()
    data  class OnFailureDataSourceConnexionEvent(val message: String) : DataSourceEvent()
    data class   OnErrorDataSourceConnexionEvent(val code:Int=0,val messages:List<ApiResponse2.ErrorMessage>):DataSourceEvent()
    data class OnSuccessDataSourceConnexionEvent<T>( val key:Any?=null ,val data:T):DataSourceEvent()
}