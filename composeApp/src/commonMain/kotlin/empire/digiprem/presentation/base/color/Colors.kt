package empire.digiprem.presentation.base.color

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object Colors {
    val background = Color(0xFFFFFFFFF)
    val primary = Color(0xFF667EEA)
    val secondary = Color(0xFFFFF6B6B)
    val surface = Color(0xFFFF8F9FA)

    val brushVioletVertical = Brush.verticalGradient(
        listOf(
            Color(0xfff7585E5),
            Color(0xfff7B72C9),
            Color(0xfff825DAC),
        )
    )
    val brushVioletLinear = Brush.linearGradient(
        listOf(
            Color(0xfff6976DE),
            Color(0xfff754FA7),
        )
    )
    val brushRoseLinear = Brush.linearGradient(
        listOf(
            Color(0xfffF091F6),
            Color(0xfffF55B75),
        )
    )
}