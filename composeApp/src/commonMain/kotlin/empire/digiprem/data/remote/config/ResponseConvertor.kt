package empire.digiprem.data.remote.config

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.converter.KtorfitResult
import de.jensklingenberg.ktorfit.converter.TypeData
import empire.digiprem.app.config.Log
import empire.digiprem.model.ApiError
import empire.digiprem.model.ApiResponse2
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers

class ApiResponse<T>(
    private var hasSuccess: Boolean = false,
    private var statusCode: Int = 0,
    private var errorMessage: String? = null,
    private var headers: Headers? = null,
    private val body: T? = null
) {
    fun status(): Int = statusCode
    fun message(): String? = errorMessage
    fun body(): T? = body
    fun hasSuccess(): Boolean = hasSuccess
    fun headers(): Headers? = headers
}

class ApiResponseConverterFactory : Converter.Factory {

    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
        if (typeData.typeInfo.type == ApiResponse::class) {
            return object : Converter.SuspendResponseConverter<HttpResponse, Any> {
                override suspend fun convert(result: KtorfitResult): Any {

                    return when (result) {
                        is KtorfitResult.Failure -> {
                            ApiResponse(
                                body = null,
                                errorMessage = result.throwable.message,
                                hasSuccess = false,
                            )
                        }

                        is KtorfitResult.Success -> {
                            val body = result.response.body(typeData.typeArgs.first().typeInfo) as Any
                            if (result.response.status.value in 200..299) {
                                val isApiRequestResult = body is ApiResponse2<*>
                                val hasSuccess = if (isApiRequestResult) (body as ApiResponse2<*>).success else true
                                ApiResponse(
                                    statusCode = result.response.status.value,
                                    body = if (isApiRequestResult) {
                                        if (hasSuccess) body else null
                                    } else body,
                                    errorMessage = if (isApiRequestResult) (body as ApiResponse2<*>).errorMessages.toString() else null,
                                    hasSuccess = if (isApiRequestResult) hasSuccess else true,
                                )
                            } else {
                                ApiResponse(
                                    statusCode = result.response.status.value,
                                    body = null,
                                    errorMessage = result.response.body<String?>(typeData.typeArgs.first().typeInfo)
                                        .toString(),
                                    hasSuccess = false,
                                )
                            }
                        }
                    }
                }
            }
        }
        return null
    }
}

class ApiResponse2ConverterFactory : Converter.Factory {

    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, ApiResponse2<*>>? {
        when(typeData.typeInfo.type){
            ApiResponse2::class->{
                return object : Converter.SuspendResponseConverter<HttpResponse, ApiResponse2<*>> {
                    override suspend fun convert(result: KtorfitResult): ApiResponse2<*> {
                        return when (result) {
                            is KtorfitResult.Failure -> {
                                ApiResponse2(
                                    payload = null,
                                    success = false,
                                    errorMessages = listOf(
                                        ApiResponse2.ErrorMessage(
                                            field = "throwable",
                                            message = result.throwable.message.toString(),
                                        )
                                    )
                                )
                            }

                            is KtorfitResult.Success -> {
                                val body = result.response.body(typeData.typeInfo) as Any
                                Log.i("KtorfitResult.Success", "body=$body")
                                Log.i("KtorfitResult.Success", "typeData.typeInfo=${typeData.typeInfo.type}")
                                if (result.response.status.value in 200..299) {
                                    (body as ApiResponse2<*>)
                                } else {
                                    ApiResponse2(
                                        payload = null,
                                        success = false,
                                        errorMessages = (body as ApiResponse2<*>).errorMessages
                                    )
                                }
                            }
                        }
                    }
                }
            }
            ApiError::class->{
                return object : Converter.SuspendResponseConverter<HttpResponse, ApiResponse2<*>> {
                    override suspend fun convert(result: KtorfitResult): ApiResponse2<*> {
                        return when (result) {
                            is KtorfitResult.Failure -> {
                                ApiResponse2(
                                    payload = null,
                                    success = false,
                                    errorMessages = listOf(
                                        ApiResponse2.ErrorMessage(
                                            field = "throwable",
                                            message = result.throwable.message.toString(),
                                        )
                                    )
                                )
                            }

                            is KtorfitResult.Success -> {
                                val body = result.response.body(typeData.typeInfo) as ApiError
                                Log.i("KtorfitResult.Success", "body=$body")

                                Log.i("KtorfitResult.Success", " (body as ApiError).message=${(body as ApiError).message}")
                                ApiResponse2(
                                    payload = null,
                                    success = false,
                                    errorMessages = (body as ApiError).errorMessages
                                )
                            }
                        }
                    }
                }
            }
            else -> {
                Log.e("ApiResponse2ConverterFactory", "Unsupported type: ${typeData.typeInfo.type}")
            }
        }


        /*   if (typeData.typeInfo.type == ApiError::class) {
               return object : Converter.SuspendResponseConverter<HttpResponse, ApiResponse2<*>> {
                   override suspend fun convert(result: KtorfitResult): ApiResponse2<*> {

                       return when (result) {
                           is KtorfitResult.Failure -> {
                               ApiResponse2(
                                   payload = null,
                                   success = false,
                                   errorMessages = listOf(
                                       ApiResponse2.ErrorMessage(
                                           field = "throwable",
                                           message = result.throwable.message.toString(),
                                       )
                                   )
                               )
                           }

                           is KtorfitResult.Success -> {
                               val body = result.response.body(typeData.typeInfo) as Any
                               Log.i("KtorfitResult.Success", "body=$body")
                               Log.i("KtorfitResult.Success", "typeData.typeInfo=${typeData.typeInfo.type}")
                               if (result.response.status.value in 200..299) {
                                   (body as ApiResponse2<*>)
                               } else {

                                   ApiResponse2(
                                       payload = null,
                                       success = false,
                                       errorMessages = (body as ApiResponse2<*>).errorMessages
                                   )
                               }
                           }
                       }
                   }
               }
           }
   */
        return null
    }
}


