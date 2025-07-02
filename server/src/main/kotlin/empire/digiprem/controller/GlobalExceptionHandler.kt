package empire.digiprem.controller

import empire.digiprem.exception.ResourceNotFoundException
import empire.digiprem.exception.ValidateTextFiledException
import empire.digiprem.extension.error
import empire.digiprem.model.ApiError
import empire.digiprem.model.ApiResponse2
import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestControllerAdvice

@OptIn(kotlin.time.ExperimentalTime::class)
@RestControllerAdvice
class GlobalExceptionHandler : ErrorController {


    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest): ResponseEntity<ApiResponse2<Any>> {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)?.toString()

        val error = ApiError(
            status = HttpStatus.BAD_REQUEST.value(),
            message = "Erreur de validation",
            path = request.requestURI,
            // errorMessages = fieldErrors
        )
        return ResponseEntity.status(status?.toIntOrNull() ?: 500).body(ApiResponse2.error(
            listOf(
                ApiResponse2.ErrorMessage(
                    field = "throwable",
                    message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE)?.toString() ?: "Erreur inconnue"
                )
            )
        ))
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(ex: ResourceNotFoundException, request: HttpServletRequest): ResponseEntity<ApiError> {
        val error = ApiError(
            status = HttpStatus.NOT_FOUND.value(),
            message = ex.message,
            path = request.requestURI,
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error)
    }

    @ExceptionHandler(ValidateTextFiledException::class)
    fun handlerValidateTextFiledException(
        ex: ValidateTextFiledException,
        request: HttpServletRequest
    ): ResponseEntity<ApiResponse2<ApiError>> {
        return ResponseEntity.ok().body(
            ApiResponse2.error(listOf(ApiResponse2.ErrorMessage(field = ex.field, message = "${ex.message}")))
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val fieldErrors = ex.bindingResult
            .fieldErrors
            .map { fieldError: FieldError ->
                ApiResponse2.ErrorMessage(
                    fieldError.field,
                    fieldError.defaultMessage.toString()
                )
            }
            .toList()
        //.collect(Collectors.toList())

        val error = ApiError(
            status = HttpStatus.BAD_REQUEST.value(),
            message = "Erreur de validation",
            path = request.requestURI,
            errorMessages = fieldErrors
        )
        return ResponseEntity.badRequest().body(error)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneral(ex: Exception, request: HttpServletRequest): ResponseEntity<ApiResponse2<Any>> {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)?.toString()

        val error = ApiError(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "Une erreur inattendue est survenue :" + ex.message,
            path = request.requestURI,
            errorMessages = listOf(ApiResponse2.ErrorMessage("throwable", "${ex.message}"))
        )
        return ResponseEntity.status(status?.toIntOrNull() ?: 500).body<ApiResponse2<Any>?>(ApiResponse2.error(
            listOf(
                ApiResponse2.ErrorMessage(
                    field = "throwable",
                    message = "internal server Error" /*request.getAttribute(RequestDispatcher.ERROR_MESSAGE)?.toString()
                        ?: ("Une erreur inattendue est survenue :" + ex.message),*/
                )
            )
        )).also {
            println("throwable=${request.getAttribute(RequestDispatcher.ERROR_MESSAGE)}")
        }
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntime(ex: RuntimeException): ResponseEntity<ApiResponse2<Any>> {
        val error = ApiError(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "Une erreur inattendue est survenue :" + ex.message,
            errorMessages = listOf(ApiResponse2.ErrorMessage("throwable", "${ex.message}"))
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ApiResponse2.error(
                listOf(ApiResponse2.ErrorMessage("throwable", "${ex.message}"))
            )
        )
    }
}