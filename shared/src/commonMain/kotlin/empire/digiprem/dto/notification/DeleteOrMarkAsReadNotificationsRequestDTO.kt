package empire.digiprem.dto.notification

import kotlinx.serialization.Serializable

@Serializable
data class DeleteOrMarkAsReadNotificationsRequestDTO(val notificationsId:List<String>)