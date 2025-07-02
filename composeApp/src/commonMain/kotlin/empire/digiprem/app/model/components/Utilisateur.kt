package empire.digiprem.app.model.components

import androidx.compose.runtime.compositionLocalOf
import empire.digiprem.enums.Role

data class Utilisateur(
    val nom: String,
    val email: String,
    val photo: String,
    val role: Role
)


