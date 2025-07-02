package empire.digiprem.model

import kotlinx.serialization.Serializable

@Serializable
data class PhoneNumber(
    val countryCode: String? = "+237",
    val phoneNumber: String = "",
)