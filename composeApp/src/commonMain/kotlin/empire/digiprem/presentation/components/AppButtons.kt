package empire.digiprem.presentation.components

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
import empire.digiprem.config.isCompactMobilePlatform

@Composable
 fun AppButton(
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement. Horizontal = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally),
    verticalAlignment: Alignment. Vertical = Alignment.CenterVertically,
     onClick: () -> Unit,
     content: @Composable (Color) -> Unit
 ) {
    Row(
        modifier = Modifier.then(modifier).fillMaxWidth().height(40.dp)
            .border(width = 1.dp, color = if(enabled) MaterialTheme.colorScheme.primary else Color.Gray,RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background( if(enabled) MaterialTheme.colorScheme.primary else Color.LightGray).clickable(enabled=enabled, onClick =onClick),
        verticalAlignment = verticalAlignment,
        horizontalArrangement =horizontalArrangement
    ) {
       content(MaterialTheme.colorScheme.surfaceVariant)
    }
}

@Composable
 fun AppSecondaryButton(
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement. Horizontal = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally),
    verticalAlignment: Alignment. Vertical = Alignment.CenterVertically,
     onClick: () -> Unit,
     content: @Composable (Color) -> Unit
 ) {
    Row(
        modifier = Modifier.then(modifier).fillMaxWidth().height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant).clickable(enabled=enabled, onClick =onClick),
        verticalAlignment = verticalAlignment,
        horizontalArrangement =horizontalArrangement
    ) {
       content(MaterialTheme.colorScheme.primary)
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

@Composable
fun AppIconActionButton(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable() (BoxScope.(Color) -> Unit)
) {
    var color:Color= Color.Black
    val isCompactSize= isCompactMobilePlatform()
    Box(
        modifier = Modifier.then(modifier).size(if (isCompactSize) 22.dp else 35.dp).clip(RoundedCornerShape(20.dp)).background(
            if (selected) {
                color=Color.White
                MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.7f
                )
            } else {
                color= Color.Black
                if(isCompactSize)  Color.Transparent else Color.White
            }
        ).clickable(enabled) { onClick() },
        contentAlignment = Alignment.Center,
        content = { content(color) }
    )
}

