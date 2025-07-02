package empire.digiprem.domain.servives.remote

import coil3.decode.DataSource
import empire.digiprem.data.config.DataSourceEvent
import kotlinx.coroutines.flow.Flow

interface IRealStateService {

  suspend  fun getAllRealEstate(): Flow<DataSourceEvent>

}