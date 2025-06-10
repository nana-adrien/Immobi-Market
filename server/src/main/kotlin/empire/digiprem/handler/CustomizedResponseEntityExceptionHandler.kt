package empire.digiprem.handler

import empire.digiprem.models.ErrorDetails
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime


/**
 * Le gestionnaire d'exceptions personnalisé sera annoté avec @ControllerAdvice pour intercepter toutes les exceptions et renvoie
 * une nouvelle ResponseEntity avec horodatage, le message d'exception et les détails de l'exception.
 */
@ControllerAdvice
class CustomizedResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest?): ResponseEntity<Any> {
        val errorDetails = ErrorDetails(LocalDateTime.now(), ex.cause!!.message!!, ex.localizedMessage)
        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}