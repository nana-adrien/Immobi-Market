package empire.digiprem.data.remote.service

import empire.digiprem.data.config.AbstractDatasourceEventController
import empire.digiprem.data.config.DataSourceEvent
import empire.digiprem.data.remote.repository.NotificationsEndPointRepository
import empire.digiprem.domain.repository.remote.INotificationsEndPointRepository
import empire.digiprem.domain.servives.remote.INotificationsService
import empire.digiprem.dto.notification.DeleteOrMarkAsReadNotificationsRequestDTO
import kotlinx.coroutines.flow.Flow

class NotificationsService: INotificationsService,AbstractDatasourceEventController() {
    private val repository:INotificationsEndPointRepository= NotificationsEndPointRepository()

    override suspend fun getAllNotifications(): Flow<DataSourceEvent> {
    return   dataSourceEventControllerEx { repository.getAllNotifications() }
    }
    override suspend fun markNotificationAsRed(notificationsId: List<String>): Flow<DataSourceEvent> {
        return   dataSourceEventControllerEx { repository.markNotificationsAsRead(DeleteOrMarkAsReadNotificationsRequestDTO(notificationsId)) }
    }
    override suspend fun deleteNotifications(notificationsId: List<String>): Flow<DataSourceEvent> {
        return   dataSourceEventControllerEx { repository.deleteNotifications(DeleteOrMarkAsReadNotificationsRequestDTO(notificationsId)) }
    }
}