package empire.digiprem.domain.repository.remote

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import empire.digiprem.dto.notification.DeleteOrMarkAsReadNotificationsRequestDTO
import empire.digiprem.dto.notification.GetNotificationResponseDTO
import empire.digiprem.model.ApiResponse2

interface INotificationsEndPointRepository {

    @GET("v1/notifications/get-all")
    fun getAllNotifications():ApiResponse2<GetNotificationResponseDTO>
    @POST("v1/notifications/delete")
    fun deleteNotifications(@Body body: DeleteOrMarkAsReadNotificationsRequestDTO): ApiResponse2<String>
    @POST("v1/notifications/mark-as-read")
    fun markNotificationsAsRead(@Body body: DeleteOrMarkAsReadNotificationsRequestDTO): ApiResponse2<String>

}


