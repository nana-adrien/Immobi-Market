package empire.digiprem.presentation.components.app

/*
@Composable
fun RealEstateItem() {

    Box(
        modifier = Modifier.shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp)).background(Color.White)
            .clickable { }
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.wrapContentSize().width(300.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(0.6f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        modifier = Modifier.height(20.dp).padding(2.dp),
                        contentDescription = null,
                    )
                    Text(
                        "Douala-Ndogbong ",
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row(
                    modifier = Modifier.weight(0.4f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.CalendarToday,
                        modifier = Modifier.height(20.dp).padding(2.dp),
                        contentDescription = null,
                    )
                    Text("10/20/25", overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.bodySmall)
                }


            }

            Column(
                modifier = Modifier.fillMaxWidth().height(250.dp).padding(vertical = 5.dp),
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
                    Text(
                        text = "Terrain Titrer",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text("Acheter:", style = MaterialTheme.typography.bodyMedium)
                    Text("50.000cfa/m2", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                }
                Box(
                    modifier = Modifier.fillMaxSize().padding(vertical = 5.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.images),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }

            val tabTemp = listOf(
                Icons.Default.Workspaces,
                Icons.Default.CarCrash,
                Icons.Default.Phone,
                Icons.Default.Notifications,
                Icons.Default.WifiProtectedSetup,
                Icons.Default.Send,
            )
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth().height(70.dp).padding(2.dp),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(tabTemp.size) {
                    Row(
                        modifier = Modifier//.shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            tabTemp[it],
                            modifier = Modifier.height(25.dp).padding(2.dp),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Text("{$it}00m2", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().height(50.dp).padding(2.dp),
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f).shadow(elevation = 10.dp, shape = RoundedCornerShape(5.dp))
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Default.Handshake,
                        modifier = Modifier.height(40.dp).padding(2.dp),
                        contentDescription = null,
                    )
                    Text("0", style = MaterialTheme.typography.bodyMedium)
                }

                Row(
                    modifier = Modifier.weight(1f).shadow(elevation = 10.dp, shape = RoundedCornerShape(5.dp))
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ) {
                    Icon(
                        Icons.Default.Favorite,
                        modifier = Modifier.height(40.dp).padding(2.dp),
                        contentDescription = null,
                    )
                }

                Row(
                    modifier = Modifier.height(40.dp).weight(1f)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(5.dp))
                        .background(Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("more...", style = MaterialTheme.typography.bodyMedium)
                }

            }
        }
    }

}*/






import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.compose.ui.util.lerp
import androidx.compose.ui.window.Popup
import coil3.compose.AsyncImage
import empire.digiprem.enums.TypeDeBien
import empire.digiprem.enums.TypeDoffre
import empire.digiprem.presentation.views.onHoverReaction
import kotlinx.serialization.Serializable

@Serializable
data class Equipment(
    val iconName: String, // On ne peut pas sérialiser directement ImageVector, donc on stocke son nom
    val value: String
)

val equipmentes = listOf(
    Equipment(
        "Workspaces",
        "1000km2"
    ),
    Equipment(
        "CarCrash",
        "100"
    ),
    Equipment(
        "Phone",
        "10km"
    ),
    Equipment(
        "Notifications",
        "2"
    ),
    Equipment(
        "WifiProtectedSetup",
        "1"
    )
)

fun RealEstateType.getIcon(): ImageVector = when (this) {
    RealEstateType.ALL -> Icons.Default.Home
    RealEstateType.MAISON -> Icons.Default.House
    RealEstateType.TERRAIN -> Icons.Default.Terrain // à importer : androidx.compose.material.icons.filled.Terrain (si tu l'as)
    RealEstateType.CHAMBRE -> Icons.Default.Bed
    RealEstateType.STUDIO -> Icons.Default.VideoCameraFront
    RealEstateType.APPARTEMENT -> Icons.Default.Apartment
    RealEstateType.BUREAU -> Icons.Default.Work
    RealEstateType.BOUTIQUE -> Icons.Default.Store
}
fun RealEstateType.getColor(): Color = when (this) {
    RealEstateType.ALL -> Color(0xFF607D8B)       // Bleu-gris
    RealEstateType.MAISON -> Color(0xFF4CAF50)     // Vert
    RealEstateType.TERRAIN -> Color(0xFF795548)    // Brun
    RealEstateType.CHAMBRE -> Color(0xFF9C27B0)    // Violet
    RealEstateType.STUDIO -> Color(0xFFFF9800)     // Orange
    RealEstateType.APPARTEMENT -> Color(0xFF03A9F4) // Bleu clair
    RealEstateType.BUREAU -> Color(0xFF3F51B5)     // Indigo
    RealEstateType.BOUTIQUE -> Color(0xFFE91E63)   // Rose foncé
}

enum class RealEstateType {
    ALL,
    MAISON,
    TERRAIN,
    CHAMBRE,
    STUDIO,
    APPARTEMENT,
    BUREAU,
    BOUTIQUE;

    companion object {
        fun getType(typeDeBien: TypeDeBien): RealEstateType {
            return when(typeDeBien){
                TypeDeBien.MAISON->MAISON
                TypeDeBien.CHAMBRE -> CHAMBRE
                TypeDeBien.STUDIO -> STUDIO
                TypeDeBien.APPARTEMENT -> APPARTEMENT
                TypeDeBien.VILLA -> MAISON
                TypeDeBien.TERRAIN -> TERRAIN
                TypeDeBien.BUREAU -> BUREAU
                TypeDeBien.ESPACE_COMMERCIAL ->BOUTIQUE
                else ->MAISON
            }
        }
    }
}

enum class RealEstateCategories {
    A_VENDRE,
    A_LOUER;

    companion object {
        fun getCategories(typeDoffre: TypeDoffre): RealEstateCategories {
            return when(typeDoffre){
                TypeDoffre.A_VENDRE->A_VENDRE
                    else->A_LOUER
            }
        }
    }
}

enum class RealEstateState{
    All,
    ON_HOLD,
    ACTIVE,
    EXPIRED
}


@Serializable
data class RealEstateData(
    val id: String="",
    val title: String="",
    val location: String="",
    val price: String="",
    val postedAgo: String="",
    val type: RealEstateType=RealEstateType.MAISON,
    val state: RealEstateState=RealEstateState.ACTIVE,
    val categories: RealEstateCategories=RealEstateCategories.A_VENDRE,
    val equipment: List<Equipment> = emptyList(),
    val images: List<String> = emptyList(),
)

fun getIconByName(name: String): ImageVector {
    return when (name) {
        "Workspaces" -> Icons.Default.Workspaces
        "CarCrash" -> Icons.Default.CarCrash
        "Phone" -> Icons.Default.Phone
        "Notifications" -> Icons.Default.Notifications
        "WifiProtectedSetup" -> Icons.Default.WifiProtectedSetup
        else -> Icons.Default.Help
    }
}

@Composable
fun RealEstateItem2(
    modifier: Modifier=Modifier,
    location: String,
    postedAgo: String,
    title: String,
    price: String,
    image: String,
    type: RealEstateType,
    categories: RealEstateCategories,
    equipment: List<Equipment>,
    onClick: () -> Unit
) {

    /*  var enabledPageDetail by remember { mutableStateOf(false) }
      var showPopup by remember { mutableStateOf(false) }
      var cardBounds by remember { mutableStateOf<Rect?>(null) }
      val density = LocalDensity.current*/
    /* // Animatables pour X, Y, width, height
     val offsetX = remember { Animatable(0f) }
     val offsetY = remember { Animatable(0f) }
     val width = remember { Animatable(0f) }
     val height = remember { Animatable(0f) }

     // Démarrer l’animation si demandé
     LaunchedEffect(enabledPageDetail) {
         if (enabledPageDetail && cardBounds != null) {
             offsetX.snapTo(cardBounds!!.left)
             offsetY.snapTo(cardBounds!!.top)
             width.snapTo(cardBounds!!.width)
             height.snapTo(cardBounds!!.height)

             offsetX.animateTo(200f, animationSpec = tween(500))
             offsetY.animateTo(300f, animationSpec = tween(500))
             width.animateTo(600f, animationSpec = tween(500))
             height.animateTo(400f, animationSpec = tween(500))
         }
     }*/

    val density = LocalDensity.current

    Box(
        modifier = Modifier.then(modifier).width(300.dp)
            .wrapContentHeight()
            /*.onGloballyPositioned {
                val position = it.localToRoot(Offset.Zero)
                val size = it.size
                cardBounds = Rect(position, size.toSize())
            }*/
            .onHoverReaction()
            .background(MaterialTheme.colorScheme.background)
            .clickable(onClick = { onClick() })
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            // verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().height(180.dp),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AsyncImage(
                        model = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .width(100.dp).height(20.dp)
                            .rotate(35f) // Rotation en diagonale
                            .align(Alignment.TopEnd)
                            .offset(x = 18.dp, y = -5.dp)  // Décalage pour bien le placer
                            .background(if (categories == RealEstateCategories.A_VENDRE) Color.Red else Color.Blue),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (categories == RealEstateCategories.A_VENDRE) "For sale" else "For rent",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                            )
                        )
                    }
                    Box(
                        modifier = Modifier.size(50.dp).align(Alignment.TopStart).padding(top = 20.dp, start = 20.dp).clip(CircleShape)
                            .background(Color.White).padding(5.dp), contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = type.getIcon(), contentDescription = "Home", tint = Color.Black)
                    }
                }
            }
            Column(modifier = Modifier.wrapContentSize().padding(horizontal = 10.dp).padding(bottom = 10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(0.6f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.LocationOn,
                            modifier = Modifier.height(18.dp),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Text(
                            location,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Row(
                        modifier = Modifier.weight(0.4f),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.CalendarToday,
                            modifier = Modifier.height(18.dp).padding(2.dp),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Text(
                            postedAgo,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp)
                        )
                    }

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {

                    Text(
                        modifier = Modifier.weight(1f),
                        text ="~$title",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = price,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

               /*     Row(
                        modifier = Modifier.weight(1f).padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                       *//* Icon(
                            modifier = Modifier.height(16.dp),
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "",
                            tint = Color.Gray
                        )*//*
                    }
                    Row(
                        modifier = Modifier.weight(1f).padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                    }
*/
                }
                /*FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    equipment.forEachIndexed { index, it ->
                        if (index < 3) {
                            Row(
                                modifier = Modifier//.shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
                                    .padding(2.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    getIconByName(it.iconName),
                                    modifier = Modifier.height(20.dp).padding(2.dp),
                                    contentDescription = null,
                                    *//*
                                                                        tint = Color.White*//*
                                )
                                //Text(it.value, style = MaterialTheme.typography.bodySmall,)
                            }
                        }
                    }
                }*/
            }
        }
    }
}


@Composable
fun HeroCardDemo() {
    var showDetails by remember { mutableStateOf(false) }
    var cardBounds by remember { mutableStateOf<Rect?>(null) }

    Box(Modifier.fillMaxSize()) {
        if (!showDetails) {
            Box(
                modifier = Modifier
                    .padding(32.dp)
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Blue)
                    .clickable {
                        showDetails = true
                    }
                    .onGloballyPositioned {
                        val position = it.positionInRoot()
                        val size = it.size.toSize()
                        cardBounds = Rect(position, size)
                    }
            ) {
                Text(
                    "Cliquez-moi",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        cardBounds?.let { bounds ->
            AnimatedVisibility(visible = showDetails) {
                HeroPopup(bounds) {
                    showDetails = false
                }
            }
        }
    }
}

@Composable
fun HeroPopup(startBounds: Rect, onDismiss: () -> Unit) {
    var animProgress by remember { mutableStateOf(0f) }

    // Animate the progress
    LaunchedEffect(Unit) {
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        ) { value, _ ->
            animProgress = value
        }
    }

    val targetSize = Size(400f, 400f)
    val currentSize = lerp(startBounds.size, targetSize, animProgress)
    val currentOffset = Offset(
        lerp(startBounds.left, (startBounds.center.x - targetSize.width / 2), animProgress),
        lerp(startBounds.top, (startBounds.center.y - targetSize.height / 2), animProgress)
    )

    Popup(
        alignment = Alignment.Center,
        offset = IntOffset(currentOffset.x.toInt(), currentOffset.y.toInt()),
        onDismissRequest = onDismiss
    ) {
        Box(
            Modifier
                .size(DpSize(currentSize.width.dp, currentSize.height.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Green)
                .clickable { onDismiss() }
        ) {
            Text("Détail de la carte", Modifier.align(Alignment.Center), color = Color.White)
        }
    }
}

@Composable
fun SimplePopupExample(
    showPopup: Boolean,
    onClick: () -> Unit
) {

    Box(Modifier.fillMaxSize().padding(32.dp)) {

        if (showPopup) {
            Popup(
                alignment = Alignment.TopStart,
                offset = IntOffset(100, 100),
                onDismissRequest = { onClick() } // facultatif
            ) {
                Box(
                    Modifier
                        .background(Color.LightGray)
                        .padding(16.dp)
                ) {
                    Text("Contenu du popup")
                }
            }
        }
    }
}

