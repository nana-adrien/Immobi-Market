package empire.digiprem.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import empire.digiprem.config.getActualWindowsSize

@Composable
 fun AppCardWrapper(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    var boxWidth = 700.dp
    when (getActualWindowsSize().widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            boxWidth = 350.dp
        }
        WindowWidthSizeClass.Medium -> {
            boxWidth = 550.dp
        }
    }
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier.wrapContentHeight().width(boxWidth).shadow(elevation = 10.dp, shape =RoundedCornerShape(10.dp) )
                , contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}