package empire.digiprem.dto.profile

import empire.digiprem.model.PhoneNumber
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class UpdateProfileRequest(
    val name: String?=null,
    val firstName: String ?=null,
    val profileUrl: String ?=null,
    val bornAt: LocalDate?=null,
    val userPhoneNumber: PhoneNumber? = null,
    val enableTwoFactorAuth: Boolean ?=null,
)