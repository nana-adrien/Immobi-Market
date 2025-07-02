package empire.digiprem.domain.servives.remote

import empire.digiprem.data.config.DataSourceEvent
import empire.digiprem.model.AppFile
import kotlinx.coroutines.flow.Flow

interface IFilesEndPointService {
    suspend fun sendFile(title: String, description: String, appFile: AppFile): Flow<DataSourceEvent>
    suspend fun uploadProfileImage(appFile: AppFile): Flow<DataSourceEvent>
}