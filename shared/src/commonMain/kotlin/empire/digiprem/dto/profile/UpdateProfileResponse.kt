package empire.digiprem.dto.profile

import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileResponse(
    val fields:List<String> = listOf(),
    val message:String=""
)