package empire.digiprem.dto.notification

import empire.digiprem.model.NotificationItem
import kotlinx.serialization.Serializable

@Serializable
data class GetNotificationResponseDTO(val notifications:List<NotificationItem>)