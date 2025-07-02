package empire.digiprem.dto.image

import kotlinx.serialization.Serializable

@Serializable
data class UploadProfileResponseDTO( val message:String , val imageUrl: String)
