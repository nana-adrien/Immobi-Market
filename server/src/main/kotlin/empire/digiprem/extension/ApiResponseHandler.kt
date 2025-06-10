package empire.digiprem.extension

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import empire.digiprem.model.ApiResponse2
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import java.util.List

@ControllerAdvice
class ApiResponseHandler : ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return returnType.containingClass.isAnnotationPresent(WrapApiResponse::class.java)
                || returnType.hasMethodAnnotation(WrapApiResponse::class.java)
    }

    override fun beforeBodyWrite(
        @Nullable body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        System.out.printf("Response body: %s\n", body)
        if (body is ApiResponse2<*>) {
            return body
        }
        if (body is String) {
            try {
                val response2: ApiResponse2<*> = ApiResponse2<Any?>(
                    success = true,
                            payload=body,
                            errorMessages = List.of(ApiResponse2.ErrorMessage())
                )

                val objectMapper = ObjectMapper()
                val json = objectMapper.writeValueAsString(
                    response2
                )
                return json
            } catch (e: JsonProcessingException) {
                throw RuntimeException("Erreur de conversion JSON", e)
            }
        }

        if (body is ResponseEntity<*>) {
            System.out.printf("ResponseEntity body: %s\n", body)
            val responseEntity = body
            val status = responseEntity.statusCode
            val actualBody = responseEntity.body

            if (status === HttpStatus.OK) {
                val response2: ApiResponse2<*> = ApiResponse2<Any?>(
                    success = true,payload=body,errorMessages = List.of(ApiResponse2.ErrorMessage())
                )

                return ResponseEntity(response2, status)
            }
        } else {
            val response2: ApiResponse2<*> = ApiResponse2<Any?>(
                success = true,payload=body,errorMessages = List.of(ApiResponse2.ErrorMessage())
            )
            return response2
        }
        return body
    }
}