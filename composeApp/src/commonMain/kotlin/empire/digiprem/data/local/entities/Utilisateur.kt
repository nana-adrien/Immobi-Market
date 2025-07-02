package empire.digiprem.data.local.entities

import empire.digiprem.enums.Role
import kotlinx.serialization.Serializable

@Serializable
data class Utilisateur(
    val nom: String,
    val email: String,
    val photo: String,
    val enabledlightMode: Boolean=true,
    val role: Role
)


