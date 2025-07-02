package empire.digiprem.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import composeApp.src.commonMain.ComposeResources.drawable.IM_Plan_de_travail_1
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import empire.digiprem.config.isCompactApplication
import empire.digiprem.model.AppFile
import empire.digiprem.presentation.components.AppIconActionButton
import empire.digiprem.presentation.components.AppTextField
import empire.digiprem.presentation.components.forms.Option
import empire.digiprem.presentation.components.forms.PickStorageFiles
import empire.digiprem.presentation.views.AppCardWrapperEx
import empire.digiprem.presentation.views.SelectContentTextField
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun PageAddRealState2Preview() {
    var data by remember { mutableStateOf(BienImmobilierFormUIState()) }
    BienImmobilierForm(
        state = data,
        onStateChange = {
            data = it
        }
    )
}

data class BienImmobilierFormUIState(
    var titre: String = "",
    var description: String = "",
    var superficie: String = "",
    var prix: String = "",

    // Caractéristiques
    var nombreChambre: Int = 0,
    var nombreSalleDeBain: Int = 1,
    var nombreCuisine: Int = 1,
    var garage: Boolean = false,
    var balcon: Boolean = false,

    // Types
    var typeDeBien: Option = Option("NONE", "Sélectionner..."),
    var typeDHabitation: Option = Option("NONE", "Sélectionner..."),
    var typeDoffre: Option = Option("NONE", "Sélectionner..."),

    // Images
    var images: List<String> = emptyList(),

    // Équipements
    var equipementsDisponibles: List<Option> = emptyList(),
    var equipementsSelectionnes: List<String> = emptyList()
)


@Composable
fun BienImmobilierForm(
    state: BienImmobilierFormUIState,
    onStateChange: (BienImmobilierFormUIState) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        item {




        }


    }
}
