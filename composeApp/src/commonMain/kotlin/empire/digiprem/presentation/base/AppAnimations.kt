package empire.digiprem.presentation.base

import androidx.compose.animation.*
import androidx.compose.animation.core.tween

object AppAnimations {

    val slideInTop=slideInVertically(
        // Commence au-dessus de la position finale (vers le bas)
        initialOffsetY = { -it },
        animationSpec = tween(500)
    )
    val slideOutBottom= slideOutVertically(
        // Termine en glissant vers le haut (disparition vers le haut)
        targetOffsetY = { -it },
        animationSpec = tween(500)
    )
    val slideInStart= fadeIn()+ slideInHorizontally(
        initialOffsetX = { -it }, // de la gauche
        animationSpec = tween(durationMillis = 1500)
    )
    val slideOutStart= fadeOut() + slideOutHorizontally(
        targetOffsetX = { -it }, // vers la droite
        animationSpec = tween(durationMillis = 2000)
    )




}