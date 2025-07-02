package empire.digiprem.data.remote.service

import empire.digiprem.data.config.AbstractDatasourceEventController
import empire.digiprem.data.config.DataSourceEvent
import empire.digiprem.data.remote.repository.FilesEndPointRepository
import empire.digiprem.domain.servives.remote.IFilesEndPointService
import empire.digiprem.model.AppFile
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class FilesEndPointService: AbstractDatasourceEventController(), IFilesEndPointService {

    private val repository: FilesEndPointRepository = FilesEndPointRepository()

    override suspend fun sendFile(title:String,description:String,appFile: AppFile): Flow<DataSourceEvent> {

        val part= formData{
            append("file",appFile.byteArray!!, Headers.build {
                append(HttpHeaders.ContentDisposition, "filename=${appFile.name}")
                append(HttpHeaders.ContentType, appFile.mimeTypeDescriptor)
            })

            append("title",title)
            append("description",description)
        }
        return dataSourceEventControllerEx { repository.uploadDocument(title,description,part[0])  }
    }

    override suspend fun uploadProfileImage(appFile: AppFile): Flow<DataSourceEvent> {

        val part=MultiPartFormDataContent( formData{
            append("file",appFile.byteArray!!, Headers.build {
                append(HttpHeaders.ContentDisposition, "filename=${appFile.name}")
                append(HttpHeaders.ContentType, appFile.mimeTypeDescriptor)
            })
        })
        return dataSourceEventControllerEx {repository.uploadProfileImage(part)}
    }
}