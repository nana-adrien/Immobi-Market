package empire.digiprem.data.config

import empire.digiprem.model.ApiResponse2


abstract class BaseDataSourceEventHandler<T>{
    abstract suspend fun onLoading()
    abstract suspend fun onFailConnexion(message: String)
    abstract suspend fun onErrorConnexion(
        code: Int,
        messages: List<ApiResponse2.ErrorMessage>
    )
    abstract  suspend fun onFailProcess()
    abstract suspend fun onSuccessConnexion(
        key: Any?,
        data: T
    )
}
abstract class DataSourceEventHandlerDecorator<T>() : BaseDataSourceEventHandler<T>() {
    private var handler: BaseDataSourceEventHandler<T>?=null
    fun setHandler(handler: BaseDataSourceEventHandler<T>){
        this.handler=handler
    }
    override suspend fun onLoading() {
        handler?.onLoading()
    }
    override suspend fun onFailConnexion(message: String) {
        handler?.onFailConnexion(message)
    }
    override suspend fun onErrorConnexion(code: Int, messages: List<ApiResponse2.ErrorMessage>) {
        handler?.onErrorConnexion(code,messages)
    }
    override suspend fun onSuccessConnexion(key: Any?, data: T) {
        handler?.onSuccessConnexion(key,data)
    }
    override suspend fun onFailProcess() {
    }
}

