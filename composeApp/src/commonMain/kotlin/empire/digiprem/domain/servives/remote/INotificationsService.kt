package empire.digiprem.domain.servives.remote

import empire.digiprem.data.config.DataSourceEvent
import kotlinx.coroutines.flow.Flow

interface INotificationsService {
    suspend fun getAllNotifications():Flow<DataSourceEvent>
    suspend fun markNotificationAsRed(notificationsId:List<String>):Flow<DataSourceEvent>
    suspend fun deleteNotifications(notificationsId:List<String>):Flow<DataSourceEvent>
}