package empire.digiprem.presentation.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import empire.digiprem.navigation.ViewPropertyALLProperty
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.koin.compose.viewmodel.koinViewModel
import androidx.navigation.NavHostController
import coil3.Uri
import empire.digiprem.navigation.EnumView
import empire.digiprem.navigation.ViewDetailRealEstateItem
import empire.digiprem.presentation.components.AppVerticalScrollBar
import empire.digiprem.presentation.components.app.RealEstateData
import empire.digiprem.presentation.components.app.RealEstateItem2
import empire.digiprem.presentation.components.app.RealEstateState
import empire.digiprem.presentation.components.forms.FormTextFieldWrapper
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
import empire.digiprem.presentation.viewmodels.PropertyALLPropertyViewModel
import empire.digiprem.presentation.intents.PropertyALLPropertyIntent
import empire.digiprem.presentation.viewmodels.DetailRealEstateItemViewModel
import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.BienImmobilierViewModel
import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.Etape1_InfoGenerales
import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.Etape2_Localisation
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyALLPropertyView(
    viewPropertyALLProperty: ViewPropertyALLProperty,
    navController: NavHostController,
    allpropertyViewModel: PropertyALLPropertyViewModel = koinViewModel()
) {
    // val allpropertyViewModel:PropertyALLPropertyViewModel = viewModel{PropertyALLPropertyViewModel()}
    val state by allpropertyViewModel.state.collectAsState()
    val onSendIntent = allpropertyViewModel::onIntentHandler
    val scope = rememberCoroutineScope()
    var selectedIndex by remember { mutableStateOf(0) }
    var enableRealEstateDetail by remember { mutableStateOf(false) }
    var currentRealEstateData by remember { mutableStateOf<RealEstateData?>(null)}
    val HorizontalPagerContent: @Composable (RealEstateState) -> Unit = { realEstateState ->

        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp).fillMaxSize(),
            columns = GridCells.Adaptive(250.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ){
            items(items = state.realEstates.filter { if (realEstateState == RealEstateState.All) true else it.state == realEstateState }) {
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
                        navController.navigate(
                            ViewDetailRealEstateItem(
                                realEstateId = it.id
                            )
                        )

                    }
                )
            }
        }

    }

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        val currentTab = tabPositions[selectedIndex]
        val indicatorOffset by animateDpAsState(
            targetValue = currentTab.left,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "IndicatorOffset"
        )
        val indicatorWidth by animateDpAsState(
            targetValue = currentTab.width,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "IndicatorWidth"
        )

        Box(
            Modifier
                .fillMaxHeight()
                .wrapContentSize(align = Alignment.BottomStart)
                .offset(x = indicatorOffset)
                .width(indicatorWidth)
                .height(4.dp)
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(2.dp))
        )
    }

    var listTab = remember {
        mutableListOf(
            HorizontalPagerItem(
                empire.digiprem.presentation.components.NavigationItem(
                    position = 0,
                    label = "All",
                    icon = Icons.Default.List,
                    onClick = {
                    }
                ),
                {
                    HorizontalPagerContent(RealEstateState.All)
                }
            ),
            HorizontalPagerItem(
                empire.digiprem.presentation.components.NavigationItem(
                    position = 1,
                    label = "On Hold",
                    icon = Icons.Default.Pending,
                    onClick = {},
                ),
                {
                    HorizontalPagerContent(RealEstateState.ON_HOLD)
                }
            ),
            HorizontalPagerItem(
                empire.digiprem.presentation.components.NavigationItem(
                    position = 2,
                    label = "active",
                    icon = Icons.Default.HourglassTop,
                    onClick = {},
                ),
                {
                    HorizontalPagerContent(RealEstateState.ACTIVE)
                }
            ),
            HorizontalPagerItem(
                empire.digiprem.presentation.components.NavigationItem(
                    position = 3,
                    label = "Expired",
                    icon = Icons.Default.HourglassDisabled,
                    onClick = {},
                ),
                {
                    HorizontalPagerContent(RealEstateState.EXPIRED)
                }
            ),

            )
    }

    /* val listTab = listOf(
        ,
         TabItem(
             "le",
             { Box(modifier = Modifier.fillMaxSize().background(Color.Black)) }
         ),
         TabItem(
             "monde",
             { Box(modifier = Modifier.fillMaxSize().background(Color.Red)) }
         ),
         TabItem(
             "encore",
             { Box(modifier = Modifier.fillMaxSize().background(Color.Yellow)) }
         ),
         TabItem(
             "encore",
             { Box(modifier = Modifier.fillMaxSize().background(Color.Cyan)) }
         ),
     )*/
    val pageState = rememberPagerState(0) { listTab.size }

    WebDesktopPageWrapper(
        modifier = Modifier,
        view = EnumView.ViewPropertyALLProperty,
        state = PageWrapperState(isSuccess = true),
        actions = {
            ScrollableTabRow(
                modifier = Modifier.width(150.dp),
                containerColor = MaterialTheme.colorScheme.background,
                selectedTabIndex = selectedIndex
            ) {
                listTab.forEachIndexed { index, it ->
                    Tab(
                        selected = index == pageState.currentPage,
                        modifier = Modifier.padding(5.dp),
                        onClick = {
                            scope.launch {
                                selectedIndex=index
                                pageState.scrollToPage(
                                    index
                                )
                            }
                        },
                        icon = { Icon(imageVector = it.navigationItem.icon, "") },
                        text = { Text(" ${it.navigationItem.label} ") },
                    )
                }
            }
        },
    ) {
        HorizontalPager(
            state = pageState,
            modifier = Modifier.padding(it).fillMaxSize()
        ) { page ->
            listTab.get(page).content()
        }
    }

    AnimatedVisibility(currentRealEstateData!=null){


        Dialog(
            onDismissRequest = {currentRealEstateData = null}
        ) {
            Box(modifier = Modifier.height( 800.dp).width(600.dp).clip(RoundedCornerShape(10.dp))) {
                DetailRealEstateItemView(
                    viewDetailRealEstateItem =ViewDetailRealEstateItem(),
                    navController =navController,
                    realEstateData = currentRealEstateData,
                    onClose = { currentRealEstateData = null },
                )
            }

        }
    }

    //  RealEstateForm()

}


@Composable
fun CertificationProprietairePage(
    onEnvoyer: (String, String, List<Uri>) -> Unit,
    modifier: Modifier = Modifier
) {
    var nom by remember { mutableStateOf("") }
    var numeroCni by remember { mutableStateOf("") }
    var documents by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var messageConfirmation by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Demande de certification en tant que propriétaire", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom complet") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = numeroCni,
            onValueChange = { numeroCni = it },
            label = { Text("Numéro de CNI") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Documents justificatifs (ex : CNI, titre foncier)", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        // Simule l’ajout de document avec un bouton (vous pouvez intégrer un vrai sélecteur plus tard)
        Button(
            onClick = {
                // Simuler un ajout de document
                // Dans une vraie app, ouvrir un file picker ici
                // documents = documents + Uri.parse("document_${documents.size + 1}")
            }
        ) {
            Icon(Icons.Default.Upload, contentDescription = "Ajouter")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ajouter un document")
        }

        documents.forEachIndexed { index, uri ->
            Text("📄 Document ${index + 1} : $uri", style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                onEnvoyer(nom, numeroCni, documents)
                messageConfirmation = "Votre demande a bien été envoyée. Elle sera traitée sous 48h."
            },
            enabled = nom.isNotBlank() && numeroCni.isNotBlank() && documents.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Envoyer la demande")
        }

        messageConfirmation?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(it, color = Color.Green, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

