package empire.digiprem.controller.notification

import empire.digiprem.dto.notification.DeleteOrMarkAsReadNotificationsRequestDTO
import empire.digiprem.dto.notification.GetNotificationResponseDTO
import empire.digiprem.extension.success
import empire.digiprem.model.ApiResponse2
import empire.digiprem.utils.database.updatedNotifiactions
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name="notifications")
@RestController
@RequestMapping("api/v1/notifications")
class NotificationControllerV2 {
    @GetMapping("/get-all")
    fun getAllNotifications(): ApiResponse2<GetNotificationResponseDTO>{
        return ApiResponse2.success(GetNotificationResponseDTO(updatedNotifiactions))
    }
    @PostMapping("/delete")
    fun deleteNotifications(@RequestBody body: DeleteOrMarkAsReadNotificationsRequestDTO): ApiResponse2<String>{
        body.notificationsId.forEach { notificationId ->
            updatedNotifiactions.removeIf { it.id==notificationId }
        }
        return ApiResponse2.success("Notification(s) supprimée(s) avec succès")
    }
    @PostMapping("/mark-as-read")
    fun markNotificationsAsRead(@RequestBody body: DeleteOrMarkAsReadNotificationsRequestDTO): ApiResponse2<String>{
        body.notificationsId.forEach { notificationId ->
            val notification=updatedNotifiactions.find { it.id == notificationId }
            if (notification != null) {
                updatedNotifiactions.removeIf { it.id == notificationId }
                updatedNotifiactions.add(notification.copy(isRead = true))
            }
        }
        return ApiResponse2.success("Notification(s) supprimée(s) avec succès")
    }
}