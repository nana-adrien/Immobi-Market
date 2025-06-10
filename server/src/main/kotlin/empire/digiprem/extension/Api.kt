package empire.digiprem.extension

import empire.digiprem.model.ApiResponse2
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CLASS
)
@Retention(
    AnnotationRetention.RUNTIME
)
@Operation(
    responses = [ApiResponse(
        responseCode = "200",
        description = "Succès",
        content = arrayOf(Content(mediaType = "application/json", schema = Schema(implementation = ApiResponse2::class)))
    )]
)
annotation class Api200