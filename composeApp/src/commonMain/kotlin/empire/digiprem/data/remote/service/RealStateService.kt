package empire.digiprem.data.remote.service

import coil3.decode.DataSource
import empire.digiprem.data.config.AbstractDatasourceEventController
import empire.digiprem.data.config.DataSourceEvent
import empire.digiprem.data.config.DataSourceEventCollector
import empire.digiprem.data.remote.repository.RealStateEndPointRepository
import empire.digiprem.domain.servives.remote.IRealStateService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RealStateService : AbstractDatasourceEventController(), IRealStateService{
    val repository=RealStateEndPointRepository()
    override suspend fun getAllRealEstate(): Flow<DataSourceEvent> {
       return dataSourceEventControllerEx { repository.getAllRealEstate() }
    }
}