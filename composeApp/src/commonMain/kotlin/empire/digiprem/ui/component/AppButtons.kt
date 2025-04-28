package empire.digiprem.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
 fun AppButton(
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement. Horizontal = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally),
    verticalAlignment: Alignment. Vertical = Alignment.CenterVertically,
     onClick: () -> Unit,
     content: @Composable () -> Unit
 ) {
    Row(
        modifier = Modifier.then(modifier).fillMaxWidth().height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primary).clickable(enabled=enabled, onClick =onClick),
        verticalAlignment = verticalAlignment,
        horizontalArrangement =horizontalArrangement
    ) {
       content()
    }
}
@Composable
 fun AppOutlinedButton(
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement. Horizontal = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally),
    verticalAlignment: Alignment. Vertical = Alignment.CenterVertically,
     onClick: () -> Unit,
     content: @Composable () -> Unit
 ) {
    Row(
        modifier = Modifier.then(modifier).fillMaxWidth().height(40.dp)
            .border(0.7.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .clickable(enabled=enabled){ onClick() },
        verticalAlignment = verticalAlignment,
        horizontalArrangement =horizontalArrangement
    ) {
       content()
    }
}

@Composable
 fun AppIconButton(
    painter: Painter,
    contentDescription: String,
    onclick: () -> Unit,
) {
    Box(
        Modifier.size(40.dp).clip(CircleShape)
            .background(Color.White)
            .clickable(onClick = onclick)
            .padding(7.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )
    }
}


