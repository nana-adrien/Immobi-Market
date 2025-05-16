package empire.digiprem.presentation.components

import androidx.compose.runtime.Composable


data class TopBarAction(
    val currentActionName: String = "",
    val enabled: Boolean = true,
    val content: @Composable () -> Unit = {}
)