 package empire.digiprem.presentation.views

 import androidx.compose.animation.AnimatedVisibility
 import androidx.compose.animation.core.animateDpAsState
 import androidx.compose.animation.core.animateFloatAsState
 import androidx.compose.animation.core.tween
 import androidx.compose.animation.fadeOut
 import androidx.compose.foundation.*
 import androidx.compose.foundation.gestures.scrollable
 import androidx.compose.foundation.layout.*
 import androidx.compose.foundation.lazy.LazyColumn
 import androidx.compose.foundation.lazy.LazyListScope
 import androidx.compose.foundation.lazy.LazyListState
 import androidx.compose.foundation.lazy.grid.*
 import androidx.compose.foundation.lazy.rememberLazyListState
 import androidx.compose.foundation.shape.CircleShape
 import androidx.compose.foundation.shape.RoundedCornerShape
 import androidx.compose.foundation.text.KeyboardOptions
 import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.automirrored.filled.Logout
 import androidx.compose.material.icons.automirrored.filled.Sort
 import androidx.compose.material.icons.automirrored.outlined.Message
 import androidx.compose.material.icons.filled.*
 import androidx.compose.material.icons.outlined.Filter
 import androidx.compose.material.icons.outlined.Notifications
 import androidx.compose.material3.*
 import androidx.compose.runtime.*
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.draw.clip
 import androidx.compose.ui.draw.scale
 import androidx.compose.ui.draw.shadow
 import androidx.compose.ui.geometry.Offset
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.graphics.Shape
 import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
 import androidx.compose.ui.input.nestedscroll.NestedScrollSource
 import androidx.compose.ui.input.nestedscroll.nestedScroll
 import androidx.compose.ui.input.pointer.PointerEventPass
 import androidx.compose.ui.input.pointer.PointerEventType
 import androidx.compose.ui.layout.ContentScale
 import androidx.compose.ui.layout.onGloballyPositioned
 import androidx.compose.ui.platform.LocalDensity
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.text.input.KeyboardType
 import androidx.compose.ui.text.style.TextAlign
 import androidx.compose.ui.unit.Dp
 import androidx.compose.ui.unit.dp
 import androidx.compose.ui.unit.sp
 import androidx.navigation.NavHostController
 import coil3.compose.AsyncImage
 import composeApp.src.commonMain.ComposeResources.drawable.Res
 import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
 import composeApp.src.commonMain.ComposeResources.drawable.brick_wall_watermark_background
 import empire.digiprem.config.isCompactMobilePlatform
 import empire.digiprem.app.model.components.SearchFilter
 import empire.digiprem.core.utils.pointerEvent
 import empire.digiprem.data.local.DataBaseTemp
 import empire.digiprem.navigation.*
 import empire.digiprem.presentation.base.AppAnimations.slideInStart
 import empire.digiprem.presentation.base.AppAnimations.slideInTop
 import empire.digiprem.presentation.base.AppAnimations.slideOutStart
 import empire.digiprem.presentation.base.color.Colors
 import empire.digiprem.presentation.components.*
 import empire.digiprem.presentation.components.app.*
 import empire.digiprem.presentation.intents.HomeIntent
 import empire.digiprem.presentation.models.HomeModel
 import empire.digiprem.presentation.viewmodels.AllRealEstateViewModel
 import empire.digiprem.presentation.viewmodels.HomeViewModel
 import empire.digiprem.ui.Screen.DetailRealEstateItemScreen
 import empire.digiprem.ui.Screen.dashboard_screen.PageSection
 import empire.digiprem.ui.Screen.dashboard_screen.screens.notifications.Notifications
 import kotlinx.datetime.LocalDate
 import org.jetbrains.compose.resources.painterResource
 import org.koin.compose.viewmodel.koinViewModel
 import kotlin.reflect.KFunction1

 @Composable
 fun AllRealEstateView(
 viewAllRealEstate:ViewAllRealEstate,
 navController: NavHostController,
 allrealestateViewModel: AllRealEstateViewModel = koinViewModel()
 ) {
        // val allrealestateViewModel:AllRealEstateViewModel = viewModel{AllRealEstateViewModel()}
         val state by allrealestateViewModel.state.collectAsState()
         val onSendIntent=allrealestateViewModel::onIntentHandler

     val isCompactSize = isCompactMobilePlatform()
     //  HomePage1(viewHome, navController, isCompactSize, state, onSendIntent)

     var enabledMenu by remember { mutableStateOf(false) }
     var selectedText by remember { mutableStateOf("bonjour") }


     var activeTopBarAction by remember { mutableStateOf(TopBarAction()) }
     var lamd: (String, @Composable () -> Unit) -> Unit = { title, content ->
         if (activeTopBarAction.enabled) {
             if (activeTopBarAction.currentActionName != title) {
                 activeTopBarAction = activeTopBarAction.copy(
                     currentActionName = title,
                     content = content
                 )
             } else {
                 activeTopBarAction = TopBarAction()
             }
         } else {
             activeTopBarAction = activeTopBarAction.copy(
                 currentActionName = title,
                 enabled = true,
                 content = content
             )
         }
     }

     val scrollableState = rememberLazyListState(viewAllRealEstate.scrollPosition)

     Scaffold(
         containerColor = MaterialTheme.colorScheme.background,
         topBar = {
             WebDesktopHeaderAppBar(
                 navController,
             )
         }
     ) {
         Box(modifier = Modifier.padding(it)) {
             LazyColumn(
                 state = scrollableState
             ) {
                 item {
                     AppBox(
                         modifier = Modifier.background(Colors.brushVioletVertical)
                     ) {
                         Column(
                             modifier = Modifier.fillMaxWidth().wrapContentHeight()
                                 .padding(vertical = 100.dp, horizontal = 30.dp),
                             verticalArrangement = Arrangement.spacedBy(50.dp, alignment = Alignment.CenterVertically),
                             horizontalAlignment = Alignment.CenterHorizontally
                         ) {
                             Spacer(modifier = Modifier.height(15.dp))
                             Text(
                                 modifier = Modifier.fillMaxWidth(),
                                 text = "Trouvez le bien immobilier qui vous convient",
                                 style = MaterialTheme.typography.titleLarge.copy(
                                     color = Color.White,
                                     fontWeight = FontWeight.Bold,
                                     fontSize = if (isCompactSize) 30.sp else 50.sp,
                                     textAlign = TextAlign.Center,
                                     lineHeight = if (isCompactSize) 35.sp else 50.sp,
                                 )
                             )
                             if (!isCompactSize) {
                                 Text(
                                     text = "En quête d’un espace qui vous ressemble ? Chambre, appart ou maison? Louez ou achetez en toute simplicité, partout au Cameroun.",
                                     style = MaterialTheme.typography.titleLarge.copy(
                                         color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp,
                                         textAlign = TextAlign.Center,
                                     )
                                 )
                             }

                         }
                     }
                 }
                 item {
                     AppBox(
                         modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                     ) {
                         Column(
                             modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(vertical = 80.dp),
                         ) {
                             Column(
                                 modifier = Modifier.fillMaxWidth().wrapContentHeight()
                                     .padding(vertical = 80.dp, horizontal = 200.dp),
                                 horizontalAlignment = Alignment.CenterHorizontally,
                                 verticalArrangement = Arrangement.spacedBy(
                                     10.dp,
                                     alignment = Alignment.CenterVertically
                                 )
                             ) {
                                 Text(
                                     "Tous les Biens Immobiliers",
                                     style = MaterialTheme.typography.titleLarge.copy(
                                         fontWeight = FontWeight.Bold,
                                         fontSize = 28.sp,
                                         color = Color.Black
                                     ),
                                     textAlign = TextAlign.Center
                                 )
                                 Text(
                                     "Découvrez notre sélection complète de biens immobiliers disponibles avec des filtres avancés pour trouver le logement parfait",
                                     fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                                 )
                             }

                             Row(
                                 verticalAlignment = Alignment.Top,
                                 horizontalArrangement = Arrangement.spacedBy(30.dp)
                             ) {
                                 AppCardWrapperEx(
                                     modifier = Modifier.shadow(elevation = 30.dp, shape = RoundedCornerShape(7.dp))
                                         .background(MaterialTheme.colorScheme.background)
                                 ) {
                                     Column(
                                         modifier = Modifier.width(319.dp).wrapContentHeight().padding(30.dp)
                                     ) {
                                         Column(
                                             modifier = Modifier.padding(vertical = 30.dp).height(50.dp),
                                             verticalArrangement = Arrangement.spacedBy(15.dp)
                                         ) {
                                             Text(
                                                 "Filtres de Recherche",
                                                 style = MaterialTheme.typography.titleLarge.copy(
                                                     fontWeight = FontWeight.Bold,
                                                     fontSize = 20.sp,
                                                     color = Color.Black
                                                 ),
                                                 textAlign = TextAlign.Center
                                             )
                                             HorizontalDivider()
                                         }

                                         Section(
                                             title = "Type de bien"
                                         ) {
                                             SelectContentTextField(
                                                 containerColor = Color(0xfffE9E9ED),
                                                 enabledMenu = enabledMenu,
                                                 selected = selectedText,
                                                 options = listOf("bonjour", "le monde", "encore"),
                                                 onDismissRequest = {
                                                     enabledMenu = !enabledMenu
                                                 },
                                                 onValueChange = {
                                                     selectedText = it
                                                 }
                                             )
                                         }
                                         Section(
                                             title = "Localisation"
                                         ) {
                                             SelectContentTextField(
                                                 containerColor = Color(0xfffE9E9ED),
                                                 enabledMenu = enabledMenu,
                                                 selected = selectedText,
                                                 options = listOf("bonjour", "le monde", "encore"),
                                                 onDismissRequest = {
                                                     enabledMenu = !enabledMenu
                                                 },
                                                 onValueChange = {
                                                     selectedText = it
                                                 }
                                             )
                                         }
                                         Section(
                                             title = "Budget (€)"
                                         ) {
                                             Row(
                                                 verticalAlignment = Alignment.CenterVertically,
                                                 horizontalArrangement = Arrangement.spacedBy(15.dp)
                                             ) {
                                                 SelectContentTextField(
                                                     enabledMenu = enabledMenu,
                                                     selected = selectedText,
                                                     options = listOf("bonjour", "le monde", "encore"),
                                                     onDismissRequest = {
                                                         enabledMenu = !enabledMenu
                                                     },
                                                     onValueChange = {
                                                         selectedText = it
                                                     }
                                                 )
                                                 SelectContentTextField(
                                                     enabledMenu = enabledMenu,
                                                     selected = selectedText,
                                                     options = listOf("bonjour", "le monde", "encore"),
                                                     onDismissRequest = {
                                                         enabledMenu = !enabledMenu
                                                     },
                                                     onValueChange = {
                                                         selectedText = it
                                                     }
                                                 )
                                             }

                                         }
                                         Section(
                                             title = "Surface (m²)"
                                         ) {
                                             Row(
                                                 verticalAlignment = Alignment.CenterVertically,
                                                 horizontalArrangement = Arrangement.spacedBy(15.dp)
                                             ) {
                                                 SelectContentTextField(
                                                     modifier = Modifier.weight(1f),
                                                     enabledMenu = enabledMenu,
                                                     selected = selectedText,
                                                     options = listOf("bonjour", "le monde", "encore"),
                                                     onDismissRequest = {
                                                         enabledMenu = !enabledMenu
                                                     },
                                                     onValueChange = {
                                                         selectedText = it
                                                     }
                                                 )
                                                 SelectContentTextField(
                                                     modifier = Modifier.weight(1f),
                                                     enabledMenu = enabledMenu,
                                                     selected = selectedText,
                                                     options = listOf("bonjour", "le monde", "encore"),
                                                     onDismissRequest = {
                                                         enabledMenu = !enabledMenu
                                                     },
                                                     onValueChange = {
                                                         selectedText = it
                                                     }
                                                 )
                                             }

                                         }

                                         Section(
                                             title = "Nombre de pièces"
                                         ) {
                                             SelectContentTextField(
                                                 containerColor = Color(0xfffE9E9ED),
                                                 enabledMenu = enabledMenu,
                                                 selected = selectedText,
                                                 options = listOf("bonjour", "le monde", "encore"),
                                                 onDismissRequest = {
                                                     enabledMenu = !enabledMenu
                                                 },
                                                 onValueChange = {
                                                     selectedText = it
                                                 }
                                             )
                                         }
                                         Section(
                                             title = "Équipements"
                                         ) {
                                             SelectContentTextField(
                                                 modifier = Modifier.width(156.dp),
                                                 enabledMenu = enabledMenu,
                                                 selected = selectedText,
                                                 options = listOf("bonjour", "le monde", "encore"),
                                                 onDismissRequest = {
                                                     enabledMenu = !enabledMenu
                                                 },
                                                 onValueChange = {
                                                     selectedText = it
                                                 }
                                             )
                                         }

                                         Row(
                                             modifier = Modifier.fillMaxWidth(),
                                             horizontalArrangement = Arrangement.spacedBy(
                                                 20.dp,
                                                 Alignment.CenterHorizontally
                                             )
                                         ) {
                                             AppButtonEx(
                                                 modifier = Modifier.weight(1f),
                                                 onClick = {
                                                 }) { Text("Appliquer") }
                                             AppOutinedButtonEx(
                                                 modifier = Modifier.weight(1f)
                                                     .background(MaterialTheme.colorScheme.surface),
                                                 onClick = {}
                                             ) {
                                                 Text("Reinitialiser")
                                             }
                                         }
                                     }
                                 }

                                 var currentPage by remember { mutableStateOf(1) }
                                 val pageSize = 8
                                 val totalPages = (state.realEstates.size + pageSize - 1) / pageSize

                                 val currentItems = state.realEstates.paginateList(currentPage, pageSize)
                                 var componentWidth by remember { mutableStateOf(0) }

                                 AppCardWrapperEx(
                                     modifier = Modifier.shadow(elevation = 20.dp, shape = RoundedCornerShape(7.dp))
                                         .background(MaterialTheme.colorScheme.background)
                                 ) {
                                     Column(
                                         modifier = Modifier.padding(30.dp).width(889.dp).wrapContentHeight(),
                                         horizontalAlignment = Alignment.CenterHorizontally
                                     ) {
                                         Column(
                                             modifier = Modifier.padding(vertical = 26.dp).height(50.dp),
                                             verticalArrangement = Arrangement.spacedBy(15.dp)
                                         ) {
                                             Row(
                                                 modifier = Modifier.fillMaxWidth(),
                                                 horizontalArrangement = Arrangement.SpaceBetween
                                             ) {
                                                 Text("24 biens trouvés", fontSize = 16.sp)
                                                 SelectContentTextField(
                                                     modifier = Modifier.height(32.dp).width(156.dp),
                                                     enabledMenu = enabledMenu,
                                                     selected = selectedText,
                                                     options = listOf("bonjour", "le monde", "encore"),
                                                     onDismissRequest = {
                                                         enabledMenu = !enabledMenu
                                                     },
                                                     onValueChange = {
                                                         selectedText = it
                                                     }
                                                 )

                                             }
                                             HorizontalDivider()
                                         }

                                         AppLazyVerticalGrid(
                                             items = currentItems,
                                             columns = 2, verticalArrangement = Arrangement.spacedBy(30.dp),
                                             horizontalArrangement = Arrangement.spacedBy(30.dp),
                                             itemHeight = 300.dp
                                         ) { it, index ->
                                             RealEstateItem2(
                                                 modifier = Modifier.onGloballyPositioned { coordinates ->
                                                     componentWidth = coordinates.size.width  // Largeur en pixels
                                                 },
                                                 location = it.location,
                                                 postedAgo = it.postedAgo,
                                                 price = it.price,
                                                 title = it.title,
                                                 image = it.images.first(),
                                                 categories = it.categories,
                                                 type = it.type,
                                                 equipment = it.equipment,
                                                 onClick = {
                                                     //onClickRealEstateItem(it.id)
                                                 }
                                             )
                                             with(LocalDensity.current) { componentWidth.toDp() }
                                         }

                                         AppPagination(
                                             currentPage = currentPage,
                                             totalPages = totalPages,
                                             onPageSelected = { page -> currentPage = page }
                                         )
                                     }
                                 }
                             }
                         }
                     }
                 }
             }
             AppVerticalScrollBar(modifier = Modifier.align(Alignment.TopEnd), scrollableState)
         }
     }
 }