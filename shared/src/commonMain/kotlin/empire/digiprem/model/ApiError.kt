package empire.digiprem.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
@OptIn(ExperimentalTime::class)
@Serializable
data class ApiError(
    var status: Int = 0,
    var message: String? = null,
    var path: String? = null,
    @Contextual
    val instant:Instant = Clock.System.now(),
    var errorMessages: List<ApiResponse2.ErrorMessage> = emptyList()
)