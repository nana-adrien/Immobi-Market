package empire.digiprem.model

import kotlinx.serialization.Serializable

@Serializable
class ApiResponse2<T>(
     val success: Boolean = false,
     val payload: T? = null,
     val errorMessages: List<ErrorMessage>
) {
    @Serializable
    data class ErrorMessage(var field: String = "", var message: String = "")
    companion object{

    }
}
