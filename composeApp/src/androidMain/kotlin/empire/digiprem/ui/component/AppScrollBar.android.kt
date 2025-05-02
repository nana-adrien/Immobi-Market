package empire.digiprem.ui.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
actual fun AppVerticalScrollBar(
    modifier: Modifier,
    scrollState: ScrollState?,
    lazyGridState: LazyGridState?
) {

    if (scrollState != null) {
        Box(
            modifier = Modifier
                .then(modifier)
                .width(4.dp)
                .fillMaxHeight()
                .background(Color.LightGray.copy(alpha = 0.4f))
        )

        // Thumb
        val proportion = 2000f // à ajuster selon le contenu réel
        val thumbHeight = 60.dp
        val maxScroll = scrollState.maxValue

        if (maxScroll > 0) {
            val thumbOffset =
                (scrollState.value.toFloat() / maxScroll) * (300f - thumbHeight.value) // 300 = hauteur max approximative

            Box(
                modifier = Modifier
                    .then(modifier)
                    .offset(y = thumbOffset.dp)
                    .width(4.dp)
                    .height(thumbHeight)
                    .background(Color.DarkGray)
            )
        }
    }

}

