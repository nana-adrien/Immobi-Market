package empire.digiprem.presentation.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
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
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import composeApp.src.commonMain.ComposeResources.drawable.brick_wall_watermark_background
import empire.digiprem.config.isCompactMobilePlatform
import empire.digiprem.app.model.components.SearchFilter
import empire.digiprem.core.utils.pointerEvent
import empire.digiprem.navigation.*
import empire.digiprem.presentation.base.AppAnimations.slideInStart
import empire.digiprem.presentation.base.AppAnimations.slideInTop
import empire.digiprem.presentation.base.AppAnimations.slideOutStart
import empire.digiprem.presentation.base.color.Colors
import empire.digiprem.presentation.components.*
import empire.digiprem.presentation.components.app.*
import empire.digiprem.presentation.intents.HomeIntent
import empire.digiprem.presentation.models.HomeModel
import empire.digiprem.presentation.viewmodels.HomeViewModel
import empire.digiprem.ui.Screen.DetailRealEstateItemScreen
import empire.digiprem.ui.Screen.dashboard_screen.PageSection
import empire.digiprem.ui.Screen.dashboard_screen.screens.notifications.Notifications
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import kotlin.reflect.KFunction1

@Composable
fun HomeView(
    viewHome: ViewHome,
    navController: NavHostController,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val state by homeViewModel.state.collectAsState()
    val onSendIntent = homeViewModel::onIntentHandler
    val pageWrapperState by homeViewModel.pageWrapperState.collectAsState()
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

    val scrollableState = rememberLazyListState(viewHome.scrollPosition)
    val authenticateButtons = @Composable {
        Row {
            TextButton(
                colors = ButtonDefaults.textButtonColors().copy(
                    contentColor = Color.White,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = { navController.navigate(ViewRegister()) }) {
                Text("Register")
            }
            TextButton(onClick = { navController.navigate(ViewLogin()) }) {
                Text("Connexion")
            }
        }

    }
    val topBar: @Composable (Color) -> Unit = @Composable {
        /*   AppHeader(containerColor = it) {
               Row(
                   modifier = Modifier.padding(end = 25.dp),
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.spacedBy(10.dp)
               ) {

                   if (!viewHome.isConnected) {
                       authenticateButtons()
                   } else {
                       AppIconActionButton(
                           selected = activeTopBarAction.currentActionName.equals("Notifications") && activeTopBarAction.enabled,
                           onClick = {
                               lamd("Notifications") {
                                   Box(
                                       modifier = Modifier.height(250.dp).width(350.dp)
                                   ) {
                                       Notifications()
                                   }
                               }
                           },
                       ) {
                           Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "", tint = it)
                       }
                       if (isCompactSize.not()) {
                           AppIconActionButton(
                               selected = activeTopBarAction.currentActionName.equals("Message") && activeTopBarAction.enabled,
                               onClick = {
                                   lamd("Message") {
                                       Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                                           Column {
                                               Column {
                                                   Row(
                                                       modifier = Modifier.fillMaxWidth()
                                                           .padding(start = 10.dp, end = 10.dp),
                                                       verticalAlignment = Alignment.CenterVertically,
                                                       horizontalArrangement = Arrangement.SpaceBetween
                                                   ) {
                                                       Text(
                                                           text = "Message",
                                                           style = MaterialTheme.typography.titleMedium.copy(
                                                               color = MaterialTheme.colorScheme.onBackground,
                                                               fontWeight = FontWeight.Bold
                                                           ),
                                                       )

                                                       IconButton(onClick = {}) {
                                                           Icon(
                                                               imageVector = Icons.Default.MoreHoriz,
                                                               contentDescription = null
                                                           )
                                                       }

                                                   }
                                                   Row(
                                                       modifier = Modifier.fillMaxWidth(),
                                                       verticalAlignment = Alignment.CenterVertically,
                                                       horizontalArrangement = Arrangement.spacedBy(
                                                           10.dp,
                                                           alignment = Alignment.Start
                                                       )
                                                   ) {
                                                       TextButton(
                                                           onClick = {},
                                                       ) {
                                                           Text(
                                                               "all"
                                                           )
                                                       }
                                                       TextButton(
                                                           onClick = {},
                                                       ) {
                                                           Text(
                                                               "Non lu"
                                                           )
                                                       }
                                                       TextButton(
                                                           onClick = {},
                                                       ) {
                                                           Text(
                                                               "voir plus..."
                                                           )
                                                       }
                                                   }


                                               }
                                             *//*  Conversations(
                                                navController = navController
                                            )*//*
                                        }

                                    }
                                }
                            },
                        ) {
                            Icon(imageVector = Icons.AutoMirrored.Outlined.Message, contentDescription = "", tint = it)
                        }
                    } else {
                        AppIconActionButton(
                            selected = activeTopBarAction.currentActionName.equals("Filter") && activeTopBarAction.enabled,
                            onClick = {
                                lamd("Filter") {
                                    Box(modifier = Modifier.height(500.dp).width(300.dp)) {
                                        Column {
                                            RealEstateSearchForm(
                                                availableEquipments = equipmentes
                                            ) {
                                                println("Serache form=$it")
                                            }
                                        }
                                    }
                                }
                            },
                        ) {
                            Icon(imageVector = Icons.Outlined.Filter, contentDescription = "", tint = it)
                        }
                    }

                    AppIconActionButton(
                        selected = activeTopBarAction.currentActionName.equals("profil") && activeTopBarAction.enabled,
                        onClick = {
                            lamd("profil") {
                                ProfilMenu(navController)
                            }
                        },
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(Res.drawable.background_immeuble),
                            contentDescription = null,
                        )
                    }
                }

            }

            if (!isCompactSize) {
                DropdownMenu(
                    shadowElevation = 0.dp,
                    tonalElevation = 0.dp,
                    containerColor = Color.Transparent,
                    modifier = Modifier.padding(top = 10.dp, end = 20.dp).wrapContentSize()
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                        .background(Color.White),
                    expanded = activeTopBarAction.enabled,
                    onDismissRequest = {
                        activeTopBarAction = activeTopBarAction.copy(enabled = false)
                    }
                ) {
                    activeTopBarAction.content()
                    *//* Box(
                         modifier = Modifier
                             .wrapContentSize()
                             .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                             .background(Color.White)
                     ) {
                     }*//*
                }
            }
        }*/
    }


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
                    Box(modifier = Modifier.fillMaxWidth().height(600.dp)) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(Res.drawable.background_immeuble),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = ""
                        )
                        AppBox(
                            modifier = Modifier.background(Colors.brushVioletVertical)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize()
                                    .padding(vertical = 100.dp, horizontal = 30.dp),
                                verticalArrangement = Arrangement.spacedBy(
                                    50.dp,
                                    alignment = Alignment.CenterVertically
                                ),
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

                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                                ) {
                                    AppButtonEx(
                                        onClick = { navController.navigate(ViewRegister()) }
                                    ) {
                                        Text("Register", color = Color.White)
                                    }
                                    AppOutinedButtonEx(onClick = { navController.navigate(ViewLogin()) }) {
                                        Text("Connexion", color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
                item {
                    AppBox(
                        modifier = Modifier.background(MaterialTheme.colorScheme.background)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().height(450.dp).padding(50.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally)
                        ) {
                            AppCardWrapperEx(
                                modifier = Modifier.onHoverReaction().background(MaterialTheme.colorScheme.surface)
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxHeight().width(350.dp).padding(20.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Bonjour le monde ", color = Color.Black)
                                }
                            }
                            AppCardWrapperEx(
                                modifier = Modifier.onHoverReaction()
                                    .background(MaterialTheme.colorScheme.surface)
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxHeight().width(350.dp).padding(20.dp)
                                        .background(MaterialTheme.colorScheme.surface),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Bonjour le monde ", color = Color.Black)
                                }
                            }
                            AppCardWrapperEx(
                                modifier = Modifier.onHoverReaction().background(MaterialTheme.colorScheme.surface)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight().width(350.dp).padding(20.dp)
                                        .background(MaterialTheme.colorScheme.surface),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Bonjour le monde ", color = Color.Black)
                                }
                            }
                            AppCardWrapperEx(
                                modifier = Modifier.onHoverReaction()
                                    .background(MaterialTheme.colorScheme.surface)
                            ) {
                                Box(
                                    modifier = Modifier.width(350.dp).padding(20.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Bonjour le monde ")
                                }
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

                                AppCardWrapperEx(
                                    modifier = Modifier.shadow(elevation = 20.dp, shape = RoundedCornerShape(7.dp))
                                        .background(MaterialTheme.colorScheme.background)
                                ) {


                                    Column(
                                        modifier = Modifier.padding(30.dp).width(889.dp).wrapContentHeight(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        if (pageWrapperState.isLoading && state.realEstates.isEmpty()) {
                                            CircularProgressIndicator()
                                        } else if (!pageWrapperState.isSuccess && state.realEstates.isEmpty()) {
                                            Button(
                                                onClick = {
                                                    homeViewModel.onRefreshPage()
                                                }
                                            ) {
                                                Text("Retry")
                                            }
                                        } else {
                                            var currentPage by remember { mutableStateOf(1) }
                                            val pageSize = 8
                                            val totalPages = (state.realEstates.size + pageSize - 1) / pageSize

                                            val currentItems = state.realEstates.paginateList(currentPage, pageSize)
                                            var componentWidth by remember { mutableStateOf(0) }
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
                                                    image = if (it.images.isNotEmpty()) it.images.first() else "",
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
                item {
                    AppBox(
                        modifier = Modifier.background(Colors.brushRoseLinear)
                    ) {
                        Column(
                            modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 60.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(30.dp)
                        ) {
                            Text(
                                "Propriétaires Certifiés de Confiance",
                                color = MaterialTheme.colorScheme.background,
                                fontWeight = FontWeight.Bold,
                                fontSize = 40.sp
                            )
                            Text(
                                "Notre processus de certification rigoureux garantit la fiabilité de chaque propriétaire sur notre plateforme",
                                color = MaterialTheme.colorScheme.background,
                                fontSize = 16.sp
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth().height(250.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally)
                            ) {
                                repeat(4) {
                                    AppCardWrapperEx(
                                        modifier = Modifier.clip(RoundedCornerShape(7.dp))
                                            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.3f))
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxHeight().weight(1f).padding(horizontal = 30.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically)
                                        ) {
                                            Text(
                                                "2,500+",
                                                color = MaterialTheme.colorScheme.background,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 30.sp
                                            )
                                            Text(
                                                "Propriétaires Certifiés ",
                                                color = MaterialTheme.colorScheme.background,
                                                fontSize = 14.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
                item {
                    AppBox(
                        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                    ) {
                        Column(
                            modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 30.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(30.dp)
                        ) {
                            Text(
                                "Nos Meilleurs Propriétaires Certifiés",
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally)
                            ) {
                                repeat(4) {
                                    AppCardWrapperEx(
                                        modifier = Modifier.weight(1f).onHoverReaction(baseElevation = 0.dp)
                                            .background(Color(0xfffF8F9FA))
                                    ) {
                                        Column(
                                            modifier = Modifier.wrapContentHeight().padding(20.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(15.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier.size(100.dp).padding(10.dp)
                                                    .clip(CircleShape)
                                                    .background(Colors.brushVioletLinear),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    "M",
                                                    color = MaterialTheme.colorScheme.background,
                                                    fontWeight = FontWeight.W400,
                                                    fontSize = 40.sp
                                                )
                                            }

                                            Text(
                                                "Marie Dupont",
                                                color = MaterialTheme.colorScheme.background,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 30.sp
                                            )
                                            Row(
                                                modifier = Modifier.clip(RoundedCornerShape(15.dp))
                                                    .background(Color.Green.copy(0.2f)).padding(10.dp)
                                            ) {
                                                Text(
                                                    "Certifié depuis 3 ans",
                                                    color = MaterialTheme.colorScheme.background
                                                )
                                            }

                                            Text(
                                                "12 biens immobiliers ",
                                                color = MaterialTheme.colorScheme.primary,
                                                fontSize = 16.sp
                                            )

                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                repeat(5) {
                                                    Icon(
                                                        imageVector = Icons.Default.Star, "",
                                                        tint = Color.Yellow
                                                    )
                                                }
                                                Text("(32 avis)", color = Color.Yellow)
                                            }

                                            AppButtonEx(onClick = {}) {
                                                Text("Voir ses biens", color = MaterialTheme.colorScheme.background)
                                            }

                                        }
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


@Composable
fun Section(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            ),
        )
        content()
    }

}


@Composable
fun Modifier.onHoverReaction(
    shape: Shape = RoundedCornerShape(7.dp),
    hoverElevation: Dp = 30.dp,
    baseElevation: Dp = 20.dp,
): Modifier {
    var hover by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(
        targetValue = if (hover) hoverElevation else baseElevation,
        animationSpec = tween(300),
        label = "hoverShadow"
    )
    val offsetY by animateDpAsState(
        targetValue = if (hover) (-5).dp else 0.dp,
        animationSpec = tween(300),
        label = "hoverOffset"
    )
    return this.then(
        Modifier.offset(y = offsetY).shadow(
            elevation = elevation,
            shape = shape,
            // spotColor = MaterialTheme.colorScheme.surface
        )
            .pointerEvent(
                eventType = PointerEventType.Enter,
                pass = PointerEventPass.Initial
            ) {
                hover = true
            }.pointerEvent(
                eventType = PointerEventType.Exit,
                pass = PointerEventPass.Initial
            ) {
                hover = false
            }
    )

}


@Composable
fun AppButtonEx(
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {

    Row(
        modifier = Modifier.then(modifier).wrapContentWidth()
            .height(50.dp)
            .onHoverReaction(baseElevation = 2.dp, hoverElevation = 10.dp)
            .background(MaterialTheme.colorScheme.secondary)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        content = content
    )
}


@Composable
fun AppOutinedButtonEx(
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier.then(modifier).wrapContentWidth()
            //.shadow(elevation = 1.dp, shape = RoundedCornerShape(7.dp))
            .height(50.dp)
            .onHoverReaction(hoverElevation = 0.dp, baseElevation = 0.dp)
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(7.dp)
            )
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        content = content
    )
}


@Composable
fun SelectContentTextField(
    enabledMenu: Boolean = false,
    onDismissRequest: () -> Unit,
    containerColor: Color = Color.Transparent,
    modifier: Modifier = Modifier,
    options: List<String>,
    selected: String = "",
    onValueChange: (String) -> Unit,
) {
    var componentWidth by remember { mutableStateOf(0) }


    Column(
        modifier = Modifier.fillMaxWidth()
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(7.dp))
            .height(41.dp)
            .onGloballyPositioned { coordinates ->
                componentWidth = coordinates.size.width  // Largeur en pixels
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize().background(containerColor).clickable { onDismissRequest() }.padding(start = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(selected, fontWeight = FontWeight.W600, fontSize = 14.sp)
            Icon(Icons.Default.ArrowDropDown, contentDescription = "")

        }
        AnimatedVisibility(enabledMenu) {
            DropdownMenu(
                modifier = Modifier.width(with(LocalDensity.current) { componentWidth.toDp() }).padding(5.dp),
                expanded = enabledMenu,
                shape = RoundedCornerShape(7.dp),
                containerColor = MaterialTheme.colorScheme.surface,
                onDismissRequest = onDismissRequest,
            ) {
                options.forEach {
                    Row(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight().background(
                            if (selected == it) {
                                MaterialTheme.colorScheme.primary.copy(0.5f)
                            } else {
                                MaterialTheme.colorScheme.surface
                            }
                        ).clickable {
                            onValueChange(it)
                            onDismissRequest()
                        }.padding(start = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = it, fontWeight = FontWeight.W600, fontSize = 14.sp, color =  if (selected == it) MaterialTheme.colorScheme.surface else Color.Black)
                    }
                }
            }
        }
    }
}


@Composable
fun AppBox(
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().then(modifier),
        contentAlignment = Alignment.Center
    ) {
        if (enabled) {
            Box(modifier = Modifier.wrapContentHeight().width(1238.dp)) {
                content()
            }
        } else {
            content()
        }
    }
}

@Composable
fun AppCardWrapperEx(
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    scrollableState: ScrollState = rememberScrollState(0),
    enabledScroll: Boolean = false,
    content: @Composable () -> Unit
) {
    val boxModifier = if (enabled) Modifier.wrapContentSize().then(modifier) else Modifier.wrapContentSize()

    Box(
        modifier = boxModifier,
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            content()
        }
        /*  if (enabledScroll){
              AppVerticalScrollBar(modifier=Modifier.align(Alignment.TopEnd), scrollState = scrollableState)
          }*/
    }
}

@Composable
private fun HomePage1(
    viewHome: ViewHome,
    navController: NavHostController,
    isCompactSize: Boolean,
    state: HomeModel,
    onSendIntent: KFunction1<HomeIntent, Unit>
) {
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

    val scrollableState = rememberLazyListState(viewHome.scrollPosition)
    val authenticateButtons = @Composable {
        Row {
            TextButton(
                colors = ButtonDefaults.textButtonColors().copy(
                    contentColor = Color.White,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = { navController.navigate(ViewRegister()) }) {
                Text("Register")
            }
            TextButton(onClick = { navController.navigate(ViewLogin()) }) {
                Text("Connexion")
            }
        }

    }
    val topBar: @Composable (Color) -> Unit = @Composable {
        AppHeader(containerColor = it) {
            Row(
                modifier = Modifier.padding(end = 25.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                if (!viewHome.isConnected) {
                    authenticateButtons()
                } else {
                    AppIconActionButton(
                        selected = activeTopBarAction.currentActionName.equals("Notifications") && activeTopBarAction.enabled,
                        onClick = {
                            lamd("Notifications") {
                                Box(
                                    modifier = Modifier.height(250.dp).width(350.dp)
                                ) {
                                    Notifications()
                                }
                            }
                        },
                    ) {
                        Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "", tint = it)
                    }
                    if (isCompactSize.not()) {
                        AppIconActionButton(
                            selected = activeTopBarAction.currentActionName.equals("Message") && activeTopBarAction.enabled,
                            onClick = {
                                lamd("Message") {
                                    Box(modifier = Modifier.height(300.dp).width(300.dp)) {
                                        Column {
                                            Column {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth()
                                                        .padding(start = 10.dp, end = 10.dp),
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.SpaceBetween
                                                ) {
                                                    Text(
                                                        text = "Message",
                                                        style = MaterialTheme.typography.titleMedium.copy(
                                                            color = MaterialTheme.colorScheme.onBackground,
                                                            fontWeight = FontWeight.Bold
                                                        ),
                                                    )

                                                    IconButton(onClick = {}) {
                                                        Icon(
                                                            imageVector = Icons.Default.MoreHoriz,
                                                            contentDescription = null
                                                        )
                                                    }

                                                }
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(
                                                        10.dp,
                                                        alignment = Alignment.Start
                                                    )
                                                ) {
                                                    TextButton(
                                                        onClick = {},
                                                    ) {
                                                        Text(
                                                            "all"
                                                        )
                                                    }
                                                    TextButton(
                                                        onClick = {},
                                                    ) {
                                                        Text(
                                                            "Non lu"
                                                        )
                                                    }
                                                    TextButton(
                                                        onClick = {},
                                                    ) {
                                                        Text(
                                                            "voir plus..."
                                                        )
                                                    }
                                                }


                                            }
                                            /* Conversations(
                                                 navController = navController
                                             )*/
                                        }

                                    }
                                }
                            },
                        ) {
                            Icon(imageVector = Icons.AutoMirrored.Outlined.Message, contentDescription = "", tint = it)
                        }
                    } else {
                        AppIconActionButton(
                            selected = activeTopBarAction.currentActionName.equals("Filter") && activeTopBarAction.enabled,
                            onClick = {
                                lamd("Filter") {
                                    Box(modifier = Modifier.height(500.dp).width(300.dp)) {
                                        Column {
                                            RealEstateSearchForm(
                                                availableEquipments = equipmentes
                                            ) {
                                                println("Serache form=$it")
                                            }
                                        }
                                    }
                                }
                            },
                        ) {
                            Icon(imageVector = Icons.Outlined.Filter, contentDescription = "", tint = it)
                        }
                    }

                    AppIconActionButton(
                        selected = activeTopBarAction.currentActionName.equals("profil") && activeTopBarAction.enabled,
                        onClick = {
                            lamd("profil") {
                                ProfilMenu(navController) {
                                    activeTopBarAction = activeTopBarAction.copy(enabled = false)
                                }
                            }
                        },
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(Res.drawable.background_immeuble),
                            contentDescription = null,
                        )
                    }
                }

            }

            if (!isCompactSize) {
                DropdownMenu(
                    shadowElevation = 0.dp,
                    tonalElevation = 0.dp,
                    containerColor = Color.Transparent,
                    modifier = Modifier.padding(top = 10.dp, end = 20.dp).wrapContentSize()
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                        .background(Color.White),
                    expanded = activeTopBarAction.enabled,
                    onDismissRequest = {
                        activeTopBarAction = activeTopBarAction.copy(enabled = false)
                    }
                ) {
                    activeTopBarAction.content()
                    /* Box(
                         modifier = Modifier
                             .wrapContentSize()
                             .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                             .background(Color.White)
                     ) {
                     }*/
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                if (!isCompactSize) {
                    AnimatedVisibility(
                        scrollableState.firstVisibleItemIndex > 0,
                        enter = slideInTop,
                        exit = fadeOut()
                    ) {
                        topBar(Color.White)
                    }
                }
            }
        ) {
            LazyColumn(
                state = scrollableState
            ) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(if (isCompactSize) 300.dp else 800.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isCompactSize.not()) {
                            Image(
                                painter = painterResource(Res.drawable.background_immeuble),
                                null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(modifier = Modifier.align(Alignment.TopStart)) {
                                    topBar(Color.Transparent)
                                }
                                Column(
                                    modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f),
                                    verticalArrangement = Arrangement.spacedBy(
                                        25.dp,
                                        alignment = Alignment.CenterVertically
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Text(
                                        modifier = Modifier.width(700.dp),
                                        text = "Trouvez le bien immobilier qui vous convient",
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = if (isCompactSize) 30.sp else 50.sp,
                                            textAlign = TextAlign.Center,
                                            lineHeight = if (isCompactSize) 35.sp else 50.sp,
                                        )
                                    )
                                    if (isCompactSize) {
                                        authenticateButtons()
                                    } else {
                                        Text(
                                            modifier = Modifier.width(700.dp),
                                            text = "En quête d’un espace qui vous ressemble ? Chambre, appart ou maison? Louez ou achetez en toute simplicité, partout au Cameroun.",
                                            style = MaterialTheme.typography.titleLarge.copy(
                                                color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp,
                                                textAlign = TextAlign.Center,
                                            )
                                        )

                                        Spacer(modifier = Modifier.height(25.dp))
                                        RealEstateSearchForm(
                                            availableEquipments = equipmentes
                                        ) {
                                            println("Serache form=$it")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                MarketplaceScreen(
                    scrollableState,
                    navController,
                    viewHome = viewHome,
                    homeModel = state,
                    onSendIntent = onSendIntent,
                    topBar = topBar,
                    authenticateButtons = authenticateButtons
                )
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(if (isCompactSize) 300.dp else 600.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isCompactSize.not()) {
                            Image(
                                painter = painterResource(Res.drawable.brick_wall_watermark_background),
                                null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(modifier = Modifier.align(Alignment.TopStart)) {
                                    topBar(Color.Transparent)
                                }
                                Column(
                                    modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f),
                                    verticalArrangement = Arrangement.spacedBy(
                                        25.dp,
                                        alignment = Alignment.CenterVertically
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Text(
                                        modifier = Modifier.width(700.dp),
                                        text = "Trouvez le bien immobilier qui vous convient",
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = if (isCompactSize) 30.sp else 50.sp,
                                            textAlign = TextAlign.Center,
                                            lineHeight = if (isCompactSize) 35.sp else 50.sp,
                                        )
                                    )
                                    if (isCompactSize) {
                                        authenticateButtons()
                                    } else {
                                        Text(
                                            modifier = Modifier.width(700.dp),
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
                    }
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(modifier = Modifier.align(Alignment.TopStart)) {
                                topBar(Color.Transparent)
                            }
                        }
                    }
                }
            }
        }

    }
}

fun LazyListScope.MarketplaceScreen(
    state: LazyListState,
    navigationController: NavHostController,
    viewHome: ViewHome,
    onSendIntent: KFunction1<HomeIntent, Unit>,
    homeModel: HomeModel,
    topBar: @Composable (Color) -> Unit,
    authenticateButtons: @Composable () -> Unit
) {
    //val route=navigationController.currentBackStackEntryAsState().value?.toRoute<Produits>()

    item {
        var selectedCategories by remember { mutableStateOf(0) }
        val gridState = rememberLazyGridState()
        // var isCompactSize= isCompactWindowSize()
        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    return Offset.Zero
                }

                override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                    state.dispatchRawDelta(-consumed.y)
                    return Offset.Zero
                }
            }
        }
        val isCompactSize = isCompactMobilePlatform()

        val onClickRealEstateItem: (String) -> Unit = {
            navigationController.navigate(
                if (isCompactSize) {
                    ViewDetailRealEstateItem(
                        realEstateId = it
                    )
                } else {
                    viewHome.copy(
                        realEstateId = it,
                        scrollPosition = state.firstVisibleItemIndex
                    )
                }
            )
        }

        Box(modifier = Modifier.padding(horizontal = 50.dp).fillMaxSize()) {
            NavigationApp(
                modifier = Modifier.wrapContentHeight(),
                navigationRail = NavigationTypeEnum.NAVIGATION_RAIL,
                firstContent = {
                    Box(
                        modifier = Modifier.heightIn(max = 700.dp).border(width = 1.dp, color = Color.LightGray)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        val scrollableState = rememberScrollState()
                        Box(modifier = Modifier.fillMaxSize()) {
                            RealEstateFilter(
                                scrollableState,
                                onCancel = {
                                    onSendIntent(HomeIntent.OnFilterRealEstates(null))
                                },
                                onSend = {
                                    onSendIntent(HomeIntent.OnFilterRealEstates(it))
                                }
                            )
                            AppVerticalScrollBar(
                                modifier = Modifier.fillMaxHeight().align(Alignment.CenterEnd),
                                scrollableState
                            )
                        }
                    }
                },
                secondContent = {
                    Box(
                        modifier = Modifier.heightIn(max = 1000.dp)/*.verticalScroll(state)*/,
                        contentAlignment = Alignment.Center
                    ) {


                        val modifier = if (isCompactSize) {
                            Modifier.fillMaxWidth()
                                .align(Alignment.Center)
                        } else {
                            Modifier.padding(top = 700.dp).fillMaxWidth()
                                .align(Alignment.Center)
                        }

                        Column(
                            modifier = Modifier.padding(start = 20.dp)
                        ) {

                            if (isCompactSize) {
                                ScrollableTabRow(
                                    selectedTabIndex = selectedCategories,
                                    containerColor = MaterialTheme.colorScheme.background,
                                ) {
                                    RealEstateType.entries.forEachIndexed { index, item ->
                                        AssistChip(
                                            modifier = Modifier.padding(horizontal = 10.dp),
                                            colors = AssistChipDefaults.assistChipColors()
                                                .copy(containerColor = item.getColor()),
                                            onClick = {
                                                selectedCategories = index
                                                onSendIntent(HomeIntent.OnFilterRealEstatesType(item))
                                            },
                                            label = { Text("${item.name.lowercase()}", color = Color.White) },
                                            leadingIcon = {
                                                Icon(item.getIcon(), "", tint = Color.White)
                                            }
                                        )

                                    }
                                }
                            }

                            RealEstateType.values().forEach { category ->

                                val filteredList = homeModel.realEstates.filter { it.type == category }
                                if (filteredList.isNotEmpty()) {
                                    val sectionTitle = when (category) {
                                        RealEstateType.CHAMBRE,
                                        RealEstateType.STUDIO -> "Chambre / Studio"

                                        else -> category.name.lowercase().replaceFirstChar { it.uppercase() }
                                    }

                                    PageSection(
                                        title = sectionTitle,
                                        modifier = Modifier.nestedScroll(connection = nestedScrollConnection), // Parallax ou suivi partiel
                                        state = gridState,
                                    ) {
                                        FlowRow(
                                            horizontalArrangement = Arrangement.spacedBy(40.dp),
                                            verticalArrangement = Arrangement.spacedBy(50.dp)
                                        ) {
                                            filteredList.forEach {
                                                RealEstateItem2(
                                                    location = it.location,
                                                    postedAgo = it.postedAgo,
                                                    price = it.price,
                                                    title = it.title,
                                                    image = it.images.first(),
                                                    categories = it.categories,
                                                    type = it.type,
                                                    equipment = it.equipment,
                                                    onClick = {
                                                        onClickRealEstateItem(it.id)
                                                    }
                                                )
                                            }
                                        }
                                    }
                                }


                                /*PageSection(
                                title = "Maison/Appartement",
                                modifier = Modifier.nestedScroll(connection = nestedScrollConnection),// Parallax ou suivi partiel
                                state = gridState,
                                onClickItem = {
                                    // enabledPageDetail = true

                                }
                            )
                            PageSection(
                                title = "Bureau / Boutique",
                                modifier = Modifier.nestedScroll(connection = nestedScrollConnection), // Parallax ou suivi partiel
                                state = gridState,
                                onClickItem = {
                                    //enabledPageDetail = true
                                }
                            )
                            PageSection(
                                title = "Terrain",
                                modifier = Modifier.nestedScroll(connection = nestedScrollConnection), // Parallax ou suivi partiel
                                state = gridState,
                                onClickItem = {
                                    //  enabledPageDetail = true
                                }
                            )*/
                            }
                        }
                    }

                }
            )
            AnimatedVisibility(
                visible = state.firstVisibleItemIndex == 1,
                enter = slideInStart,
                exit = slideOutStart,
                modifier = Modifier.padding(start = 50.dp, top = 50.dp).wrapContentSize()
                    .align(Alignment.CenterStart)
            ) {
                Box(
                    modifier = Modifier.heightIn(max = 700.dp).width(300.dp)
                        .border(width = 1.dp, color = Color.LightGray)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    val scrollableState = rememberScrollState()
                    Box(modifier = Modifier.fillMaxSize()) {
                        RealEstateFilter(
                            scrollableState,
                            onCancel = {
                                onSendIntent(HomeIntent.OnFilterRealEstates(null))
                            },
                            onSend = {
                                onSendIntent(HomeIntent.OnFilterRealEstates(it))
                            }
                        )
                        AppVerticalScrollBar(
                            modifier = Modifier.fillMaxHeight().align(Alignment.CenterEnd),
                            scrollableState
                        )
                    }
                }
            }
            if (!isCompactSize) {
                /* AppVerticalScrollBar(
                     modifier = Modifier.width(5.dp).align(Alignment.CenterEnd).zIndex(0.8f),
                     scrollState = state
                 )*/

                AnimatedVisibility(viewHome.realEstateId.isNotEmpty()) {
                    AppScrollableDialog(
                        onDismissRequest = {
                            navigationController.navigateUp()
                        },
                    ) {
                        Text("bonjour le monde")
                        DetailRealEstateItemScreen(
                            navController = navigationController,
                            generateFakeRealEstateListCameroon().filter { it.id == viewHome.realEstateId }.first(),
                            onClose = {
                                navigationController.navigateUp()
                            })
                    }
                }
            }
        }
    }
}


data class RealEstateFilterData(
    val type: RealEstateType = RealEstateType.ALL
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RealEstateFilter(
    scrollableState: ScrollState,
    onSend: (RealEstateFilterData) -> Unit,
    onCancel: () -> Unit
) {
    var realEstateFilter by remember { mutableStateOf(RealEstateFilterData()) }
    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.border(width = 0.5.dp, color = Color.LightGray),
                colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Transparent),
                windowInsets = WindowInsets(0.dp),
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Filter4,
                            contentDescription = ""
                        )
                    }
                },
                title = { Text("Filtres") },
                actions = {
                    AppIconActionButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Restore,
                            contentDescription = ""
                        )
                    }
                    AppIconActionButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Sort,
                            contentDescription = ""
                        )
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().background(Color.White).padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppOutlinedButton(
                    modifier = Modifier.weight(0.5f),
                    onClick = { onCancel() }
                ) {
                    Text("Cancel")
                }
                AppButton(
                    modifier = Modifier.weight(0.5f),
                    onClick = { onSend(realEstateFilter) }
                ) {
                    Text("Filter")
                }
            }
        }
    ) {

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RealEstateSearchForm(
    modifier: Modifier = Modifier,
    availableTypes: List<RealEstateType> = RealEstateType.entries,
    availableCategories: List<RealEstateCategories> = RealEstateCategories.entries,
    availableEquipments: List<Equipment>,
    availableRegions: List<String> = listOf("Centre", "littoral"),
    getCities: (String) -> List<String> = { it ->
        if (it == "Centre") listOf(
            "YaoundeIV",
            "YaoundeI"
        ) else listOf("Douala1", "Douala")
    },
    getDistricts: (String) -> List<String> = { it ->
        when (it) {
            "YaoundeIV" -> listOf("Mvan", "Ekounou")
            "YaoundeI" -> listOf("soa", "apres soa")
            "DoualaI" -> listOf("Akwa", "Cite sicc")
            else -> listOf("Ndogbong", "Ndokoti")
        }
    },
    onSearch: (SearchFilter) -> Unit
) {
    var selectedType by remember { mutableStateOf<RealEstateType?>(null) }
    var selectedCategory by remember { mutableStateOf<RealEstateCategories?>(null) }
    var selectedEquipments by remember { mutableStateOf<List<Equipment>>(emptyList()) }

    var selectedRegion by remember { mutableStateOf<String?>(null) }
    var selectedCity by remember { mutableStateOf<String?>(null) }
    var selectedDistrict by remember { mutableStateOf<String?>(null) }

    var minPrice by remember { mutableStateOf("") }
    var maxPrice by remember { mutableStateOf("") }

    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    val datePicker = rememberDatePickerState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        FlowRow(
            modifier = Modifier.then(modifier).fillMaxWidth(),
            itemVerticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(25.dp, alignment = Alignment.CenterHorizontally),
            // verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Bottom),
        ) {
            // 📅 Date Picker
            /* Button(onClick = { datePicker  show() }) {
                 Text(text = selectedDate?.toString() ?: "Choisir une date")
             }

             if (datePicker.isVisible) {
                 DatePickerDialog(
                     onDismissRequest = { datePicker.dismiss() },
                     onDateChange = {
                         selectedDate = it
                         datePicker.dismiss()
                     }
                 )
             }*/

            // 🏷️ Type de bien
            DropdownSelector(
                label = "Type",
                options = availableTypes.map { it.name },
                selected = selectedType?.name,
                onOptionSelected = { selectedType = RealEstateType.valueOf(it) }
            )

            // 📂 Catégorie
            DropdownSelector(
                label = "Catégorie",
                options = availableCategories.map { it.name },
                selected = selectedCategory?.name,
                onOptionSelected = { selectedCategory = RealEstateCategories.valueOf(it) }
            )
            /* // 📂 Catégorie
             DropdownSelector(
                 label = "Équipements",
                 options = availableCategories.map { it.name },
                 selected = selectedCategory?.name,
                 onOptionSelected = { selectedCategory = RealEstateCategories.valueOf(it) }
             )*/

            /* // ⚙️ Équipements (Multi-sélection)
             MultiSelectorChip(
                 label = "Équipements",
                 options = availableEquipments.map { it.value },
                 selectedOptions = selectedEquipments.map { it.value },
                 onSelectionChanged = { names ->
                     selectedEquipments = availableEquipments.filter { it.value in names }
                 }
             )*/

            // 💰 Prix Min / Max
            // 📍 Région, Ville, Quartier
            DropdownSelector(
                label = "Région",
                options = availableRegions,
                selected = selectedRegion,
                onOptionSelected = {
                    selectedRegion = it
                    selectedCity = null
                    selectedDistrict = null
                }
            )

            selectedRegion?.let { region ->
                DropdownSelector(
                    label = "Ville",
                    options = getCities(region),
                    selected = selectedCity,
                    onOptionSelected = {
                        selectedCity = it
                        selectedDistrict = null
                    }
                )
            }

            selectedCity?.let { city ->
                DropdownSelector(
                    label = "Quartier",
                    options = getDistricts(city),
                    selected = selectedDistrict,
                    onOptionSelected = { selectedDistrict = it }
                )
            }
            AppTextField(
                modifier = Modifier.width(100.dp),
                value = minPrice,
                onValueChange = { minPrice = it },
                label = { Text("Prix min", style = MaterialTheme.typography.titleMedium.copy(Color.White)) },
                placeholder = "Prix min",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            AppTextField(
                modifier = Modifier.width(100.dp),
                value = maxPrice,
                onValueChange = { maxPrice = it },
                label = { Text("Prix max", style = MaterialTheme.typography.titleMedium.copy(Color.White)) },
                placeholder = "Prix max",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Button(
            modifier = Modifier.wrapContentSize()
                .padding(5.dp),
            onClick = {
                onSearch(
                    SearchFilter(
                        type = selectedType,
                        category = selectedCategory,
                        equipments = selectedEquipments,
                        minPrice = minPrice.toIntOrNull(),
                        maxPrice = maxPrice.toIntOrNull(),
                        region = selectedRegion,
                        city = selectedCity,
                        district = selectedDistrict,
                        date = selectedDate
                    )
                )
            }
        ) {
            Text("Rechercher")
        }
    }

    /*  FlowRow(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(25.dp, alignment = Alignment.CenterHorizontally),
         // verticalArrangement = Arrangement.spacedBy(25.dp, alignment = Alignment.CenterVertically),
      ) {


      }*/


}
