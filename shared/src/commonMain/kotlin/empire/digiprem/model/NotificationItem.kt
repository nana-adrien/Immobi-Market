package empire.digiprem.model

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class NotificationItem @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String=Uuid.random().toString(),
    val title: String,
    val body: String,
    val time: String,
    val isRead:Boolean=false,
)

 enum class NotificationsFilter{
     ALL,
     AS_READ,
     NOT_READ,
 }