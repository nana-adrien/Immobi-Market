package empire.digiprem.dto.profile


import empire.digiprem.enums.Role
import empire.digiprem.model.PhoneNumber
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class GetProfileResponse(
    val name: String = "",
    val firstName: String = "",
    val profileUrl: String = "",
    val bornAt: LocalDate?=null,
    val email: String = "",
    val role: Role=Role.DEMANDEUR,
    val userPhoneNumber: PhoneNumber? = null,
    val enableTwoFactorAuth: Boolean = false,
    val isEmailVerified: Boolean = false,
    val isPhoneVerified: Boolean = false,
    val isComplete: Boolean =  false,
    val isOnline: Boolean = false,
    val createdAt: LocalDateTime?=null
)