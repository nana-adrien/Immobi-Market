package empire.digiprem.controller.files

import empire.digiprem.dto.image.UploadProfileResponseDTO
import empire.digiprem.enums.FileUriTypeEnum
import empire.digiprem.extension.error
import empire.digiprem.extension.success
import empire.digiprem.model.ApiResponse2
import empire.digiprem.model.AppFile
import empire.digiprem.models.database.User
import empire.digiprem.services.file.FilesService
import empire.digiprem.utils.tools
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.uuid.ExperimentalUuidApi

@Tag(name="files")
@RestController
@RequestMapping("/api/v2/files")
class FileUploadController(
    private val filesService: FilesService,
) {
    @PostMapping("/upload",consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun upload(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("titre") title: String,
        @RequestParam("description") description: String
    ):ApiResponse2<String>{

        if (file.isEmpty()){
            return ApiResponse2.error(listOf(ApiResponse2.ErrorMessage(field = "file", message = "file can not be empty")))
        }

        // chemin de sauvegarde

        val filePath = Paths.get("uploads/${file.originalFilename}")
        Files.createDirectories(filePath.parent)
        file.transferTo(filePath)

        // traitement des autre donner( ex:sauvegade en bdd)
        println("Titre:$title - Description:$description - Path:$filePath")

        return ApiResponse2.success("Titre:$title - Description:$description - Path:$filePath")
    }

    @PostMapping("/upload2",consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun upload2(
        @RequestParam("file") file: MultipartFile,
        @RequestParam("titre") title: String,
        @RequestParam("description") description: String
    ):ApiResponse2<String>{

        if (file.isEmpty()){
            return ApiResponse2.error(listOf(ApiResponse2.ErrorMessage(field = "file", message = "file can not be empty")))
        }

        // chemin de sauvegarde

        val appFile=AppFile(
            byteArray = tools.createAvatarImage("B") ,//file.bytes,
            mimeType =FileUriTypeEnum.fromExtension(file.contentType?:"") ,
            mimeTypeDescriptor = file.contentType?:"",
            name = file.name,
        )
        return ApiResponse2.success(filesService.uploadProfile("12345",appFile))
    }


    @OptIn(ExperimentalUuidApi::class)
    @PostMapping("/upload-profile-image",consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadProfileImage(
        @RequestParam("file") file: MultipartFile,
        @AuthenticationPrincipal userDetails: UserDetails
    ):ApiResponse2<UploadProfileResponseDTO>{

        if (file.isEmpty()){
            return ApiResponse2.error(listOf(ApiResponse2.ErrorMessage(field = "file", message = "file can not be empty")))
        }

        // chemin de sauvegarde
        val appFile=AppFile(
            byteArray =file.bytes,
            mimeType =FileUriTypeEnum.fromExtension(file.contentType?:"") ,
            mimeTypeDescriptor = file.contentType?:"",
            name = file.name,
        )
        val profileUrl=filesService.uploadProfile((userDetails as User).id.toString(),appFile)
        return ApiResponse2.success(UploadProfileResponseDTO("file upload successful ",profileUrl) )
    }


}