package empire.digiprem.exception

import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver

class ValidateTextFiledException(val field:String, message:String) : RuntimeException(message)
