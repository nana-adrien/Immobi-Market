package empire.digiprem.extension

import empire.digiprem.model.ApiResponse2
import io.konform.validation.ValidationError
import org.springframework.http.ResponseEntity


    fun <T> ApiResponse2.Companion.successResponse(payload: T): ResponseEntity<ApiResponse2<T>> {
        val response = ApiResponse2<T>(
            success = true,
            payload = payload,
            errorMessages = listOf(ApiResponse2.ErrorMessage())
        )

        return ResponseEntity.ok(response)
    }
    fun <T> ApiResponse2.Companion.success(payload: T): ApiResponse2<T> {
        val response = ApiResponse2(
            success = true,
            payload = payload,
            errorMessages = listOf(ApiResponse2.ErrorMessage())
        )
        return response
    }

    fun <T> ApiResponse2.Companion.error(errorMessages: List<ApiResponse2.ErrorMessage>): ApiResponse2<T> {
        val response = ApiResponse2<T>(
            success = false,
            payload = null,
            errorMessages = errorMessages
        )
        return response
    }
    fun <T> ApiResponse2.Companion.validationError(validationError: List<ValidationError>): ApiResponse2<T> {
        val response = ApiResponse2<T>(
            success = false,
            payload = null,
            errorMessages =  validationError.map { ApiResponse2.ErrorMessage(it.dataPath,it.message) }
        )
        return response
    }





/*public static <T> ResponseEntity successResponse(T payload) {
ApiResponse response = new ApiResponse<T>();
response.setSuccess(true);
response.setPayload(payload);
return ResponseEntity.ok(response);
}*/
