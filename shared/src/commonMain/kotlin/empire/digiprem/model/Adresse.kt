package empire.digiprem.model

import kotlinx.serialization.Serializable

@Serializable
data class Adresse(
    val region: String = "",
    val city: String = "",
    val district: String = "",
)