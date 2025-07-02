package empire.digiprem.data.remote.repository

import empire.digiprem.data.remote.config.KtorfitServiceCreator
import empire.digiprem.domain.repository.remote.INotificationsEndPointRepository
import empire.digiprem.dto.notification.DeleteOrMarkAsReadNotificationsRequestDTO
import empire.digiprem.dto.notification.GetNotificationResponseDTO
import empire.digiprem.model.ApiResponse2
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotificationsEndPointRepository: INotificationsEndPointRepository,KoinComponent{
    val ktorfitService:KtorfitServiceCreator by inject ()
    override fun getAllNotifications(): ApiResponse2<GetNotificationResponseDTO> {
       return ktorfitService.apiClient().create<INotificationsEndPointRepository>().getAllNotifications()
    }
    override fun deleteNotifications(body: DeleteOrMarkAsReadNotificationsRequestDTO): ApiResponse2<String> {
        return ktorfitService.apiClient().create<INotificationsEndPointRepository>().deleteNotifications(body)
    }
    override fun markNotificationsAsRead(body: DeleteOrMarkAsReadNotificationsRequestDTO): ApiResponse2<String> {
        return ktorfitService.apiClient().create<INotificationsEndPointRepository>().markNotificationsAsRead(body)
    }
}