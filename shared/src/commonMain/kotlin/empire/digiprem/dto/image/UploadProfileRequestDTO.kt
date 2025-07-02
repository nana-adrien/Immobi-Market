package empire.digiprem.dto.image

import kotlinx.serialization.Serializable

@Serializable
data class UploadProfileRequestDTO(val image: ByteArray)
