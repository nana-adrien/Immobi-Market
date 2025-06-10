package empire.digiprem.presentation.components.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import empire.digiprem.model.AppFile

@Composable
fun ProfilePictureFileSelector(
    pictureFile: AppFile? = null,
    onClickSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(Color.Gray),
        contentAlignment = Alignment.BottomEnd
    ) {
        // Image actuelle ou placeholder
        if (pictureFile?.byteArray != null) {
            Image(
                bitmap = pictureFile.byteArray!!.decodeToImageBitmap(),
                contentDescription = "Profile",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Placeholder",
                modifier = Modifier.fillMaxSize(0.6f),
                tint = Color.White
            )
        }

        // Bouton de modification
        IconButton(
            onClick = onClickSelect,
            modifier = Modifier
                .size(32.dp)
                .background(Color.White, CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Modifier",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
