package empire.digiprem.controller.images

import com.google.firebase.internal.FirebaseService
import empire.digiprem.dto.image.UploadProfileResponseDTO
import empire.digiprem.extension.error
import empire.digiprem.extension.success
import empire.digiprem.model.ApiResponse2
import empire.digiprem.services.AppFireBaseService
import jakarta.mail.Multipart
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageHandlerController(
    private val firebaseService: AppFireBaseService,
) {

    @PostMapping("/api/users/uplad-profile")
    fun uploadProfileImage(@RequestParam("image") file:MultipartFile):ApiResponse2<UploadProfileResponseDTO> {
        try {
            val imageUrl = firebaseService.uploadImage(file.bytes, file.originalFilename ?: "default.jpg")
            return ApiResponse2.success(UploadProfileResponseDTO(imageUrl))
        } catch (e: Exception) {
            println("Error-> Error uploading image: ${e.message}")
            return ApiResponse2.error(listOf(ApiResponse2.ErrorMessage("throwable","Uploading Image Failed")))
        }
    }
}