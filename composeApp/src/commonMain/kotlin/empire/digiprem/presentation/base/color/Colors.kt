package empire.digiprem.presentation.base.color

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object Colors {
    val background = Color(0xFFFFFFFFF)
    val primary = Color(0xff000000)//Color(0xFF667EEA)
    val secondary = Color(0xffff0000) // Color(0xFFFFF6B6B)
    val surface = Color(0xFFFF8F9FA)

    val brushVioletVertical = Brush.verticalGradient(
        listOf(
         //   Color(0xfff7585E5),
            primary.copy(alpha = 0.3f),
            primary.copy(alpha = 0.5f),
            primary,
            //Color(0xfff7B72C9),
           // Color(0xfff825DAC).copy(alpha = 0.7f),
        )
    )
    val brushVioletLinear = Brush.linearGradient(
        listOf(
            primary.copy(alpha = 0.3f),
            primary.copy(alpha = 0.5f),
            primary,
          //  Color(0xfff6976DE),
        )
    )
    val brushRoseLinear = Brush.linearGradient(
        listOf(

            secondary.copy(alpha = 0.3f),
            secondary.copy(alpha = 0.5f),
            secondary,// Color(0xfffF091F6),
           // Color(0xfffF55B75),
        )
    )
}