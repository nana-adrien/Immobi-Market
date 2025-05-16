package empire.digiprem.presentation.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.Filter
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.octopusfx.mymessenger.ui.screen.Conversations
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import empire.digiprem.config.isCompactMobilePlatform
import empire.digiprem.core.database.AppDataBase.RealEstateList
import empire.digiprem.data.model.components.SearchFilter
import empire.digiprem.navigation.*
import empire.digiprem.presentation.base.AppAnimations.slideInStart
import empire.digiprem.presentation.base.AppAnimations.slideInTop
import empire.digiprem.presentation.base.AppAnimations.slideOutStart
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
    // val homeViewModel:HomeViewModel = viewModel{HomeViewModel()}
    val state by homeViewModel.state.collectAsState()
    val onSendIntent = homeViewModel::onIntentHandler
    val isCompactSize = isCompactMobilePlatform()
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

    val scrollableState = rememberScrollState(viewHome.scrollPosition)
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
                        selected = activeTopBarAction.currentActionName.equals("Notifications"),
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
                            selected = activeTopBarAction.currentActionName.equals("Message"),
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
                                            Conversations(
                                                navController = navController
                                            )
                                        }

                                    }
                                }
                            },
                        ) {
                            Icon(imageVector = Icons.Outlined.Message, contentDescription = "", tint = it)
                        }
                    } else {
                        AppIconActionButton(
                            selected = activeTopBarAction.currentActionName.equals("Filter"),
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
                        selected = activeTopBarAction.currentActionName.equals("profil"),
                        onClick = {
                            lamd("profil") {
                                Box(modifier = Modifier.height(250.dp).width(350.dp).padding(10.dp)) {
                                    Column {
                                        Row(
                                            modifier = Modifier.fillMaxWidth()
                                                .clickable { navController.navigate(ViewStatistics()) },
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text("Tableau de board")
                                        }
                                        HorizontalDivider()
                                        Row(
                                            modifier = Modifier.fillMaxWidth().clickable {
                                                navController.navigate(
                                                    ViewHome(isConnected = false)
                                                )
                                            },
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text("Deconnexion")
                                        }
                                        HorizontalDivider()
                                    }
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
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                AnimatedVisibility(
                    scrollableState.value > 50,
                    enter = slideInTop,
                    exit = fadeOut()
                ) {
                    topBar(MaterialTheme.colorScheme.surfaceVariant)
                }
            }
        ) {
            MarketplaceScreen(
                scrollableState,
                navController,
                viewHome = viewHome,
                homeModel = state,
                onSendIntent = onSendIntent,
                topBar = topBar,
                authenticateButtons = authenticateButtons
            )
        }

        AnimatedVisibility(
            visible = activeTopBarAction.enabled,//enabledNotification,
            modifier = Modifier.padding(top = 70.dp, end = 20.dp).wrapContentSize().align(Alignment.TopEnd)
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                activeTopBarAction.content()
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketplaceScreen(
    state: ScrollState,
    navigationController: NavHostController,
    viewHome: ViewHome,
    onSendIntent: KFunction1<HomeIntent, Unit>,
    homeModel: HomeModel,
    topBar: @Composable (Color) -> Unit,
    authenticateButtons: @Composable () -> Unit
) {

    //val route=navigationController.currentBackStackEntryAsState().value?.toRoute<Produits>()
    var enabledPageDetail by remember { mutableStateOf(false) }
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
                    scrollPosition = state.value
                )
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxSize().verticalScroll(state), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier.fillMaxWidth().height(if (isCompactSize) 300.dp else 600.dp)
                    .align(Alignment.TopStart).zIndex(0.8f),
                contentAlignment = Alignment.Center
            ) {
                if (isCompactSize.not()) {
                    Image(
                        painter = painterResource(Res.drawable.background_immeuble),
                        null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(modifier = Modifier.align(Alignment.TopStart)) {
                        topBar(Color.Transparent)
                    }
                    Box(
                        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight().fillMaxWidth(0.8f),
                            verticalArrangement = Arrangement.spacedBy(25.dp, alignment = Alignment.CenterVertically),
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
                } else {
                    Box(modifier = Modifier.align(Alignment.TopStart)) {
                        topBar(Color.Transparent)
                    }
                }

            }
            val modifier = if (isCompactSize) {
                Modifier.fillMaxWidth().padding(top = 60.dp, /*start = 10.dp, end = 10.dp*/)
                    .align(Alignment.Center)
            } else {
                Modifier.padding(top = 700.dp).fillMaxWidth().padding(start = 350.dp, end = 50.dp)
                    .align(Alignment.Center)
            }
            Column(
                modifier = modifier.align(Alignment.Center).zIndex(0f),
            ) {
                PageSection(
                    title = "Les plus visites",
                    modifier =
                        Modifier.nestedScroll(connection = nestedScrollConnection), // Parallax ou suivi partiel
                    state = gridState,
                ) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val liste = RealEstateList.value.subList(0, 5)
                        liste.forEach {
                            RealEstateItem2(
                                location = it.location,
                                postedAgo = it.postedAgo,
                                price = it.price,
                                title = it.title,
                                image = it.images.first(),
                                equipment = it.equipment,
                                onClick = {
                                    onClickRealEstateItem(it.id)
                                }
                            )
                        }
                    }
                }

                PageSection(
                    title = "Juste a quelque Metre",
                    modifier =
                        Modifier.nestedScroll(connection = nestedScrollConnection), // Parallax ou suivi partiel
                    state = gridState,
                ) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val liste = RealEstateList.value
                        liste.forEach {
                            RealEstateItem2(
                                location = it.location,
                                postedAgo = it.postedAgo,
                                price = it.price,
                                title = it.title,
                                image = it.images.first(),
                                equipment = it.equipment,
                                onClick = {
                                    onClickRealEstateItem(it.id)
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
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                filteredList.forEach {
                                    RealEstateItem2(
                                        location = it.location,
                                        postedAgo = it.postedAgo,
                                        price = it.price,
                                        title = it.title,
                                        image = it.images.first(),
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

        AppVerticalScrollBar(
            modifier = Modifier.width(5.dp).align(Alignment.CenterEnd).zIndex(0.8f),
            scrollState = state
        )
        if (!isCompactSize) {
            AnimatedVisibility(
                visible = state.value > 600,
                enter = slideInStart,
                exit = slideOutStart,
                modifier = Modifier.padding(start = 50.dp, top = 50.dp).wrapContentSize().align(Alignment.CenterStart)
            ) {
                Box(
                    modifier = Modifier
                        .height(450.dp)
                        .width(250.dp)
                        .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
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
        modifier = Modifier.wrapContentSize(),
        topBar = {
            TopAppBar(
                title = { Text("Speed up your research") }
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth(0.7f).background(Color.White),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AppButton(
                    onClick = { onCancel() }
                ) {
                    Text("Cancel")
                }
                AppOutlinedButton(
                    onClick = { onSend(realEstateFilter) }
                ) {
                    Text("Filter")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(scrollableState),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.Start
        ) {

            RealEstateType.entries.forEach {
                TextButton(onClick = {
                    realEstateFilter = realEstateFilter.copy(type = it)
                }) {
                    Text(it.name.lowercase().replaceFirstChar { it.uppercaseChar() })
                }
            }
            /*
            TextButton(onClick = {
                realEstateFilter=realEstateFilter.copy(type = RealEstateType.ALL)
            }) {
                Text("tout")
            }

            TextButton(onClick = {
                realEstateFilter=realEstateFilter.copy(type = RealEstateType.MAISON)
            }) {
                Text("Maison")
            }
            TextButton(onClick = {

                realEstateFilter=realEstateFilter.copy(type = RealEstateType.CHAMBRE)
            }) {
                Text("Chambre")
            }
            TextButton(onClick = {
                realEstateFilter=realEstateFilter.copy(type = RealEstateType.BUREAU)
            }) {
                Text("Bureau")
            }
            TextButton(onClick = {
                realEstateFilter=realEstateFilter.copy(type = RealEstateType.STUDIO)
            }) {
                Text("Studio")
            }
            TextButton(onClick = {
                realEstateFilter=realEstateFilter.copy(type = RealEstateType.TERRAIN)
            }) {
                Text("Terrain")
            }
            TextButton(onClick = {
                realEstateFilter=realEstateFilter.copy(type = RealEstateType.APPARTEMENT)
            }) {
                Text("Appartement")
            }*/


        }
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
