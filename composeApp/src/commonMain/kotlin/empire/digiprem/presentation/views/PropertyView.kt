package empire.digiprem.presentation.views

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import empire.digiprem.navigation.*
import empire.digiprem.presentation.components.app.RealEstateItem2
import empire.digiprem.presentation.components.app.RealEstateState
import empire.digiprem.presentation.viewmodels.PropertyViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

/*
data class TabItem(
    val title: String = "",
    val content: @Composable () -> Unit
)
*/


@Composable
fun PropertyView(
    viewProperty: ViewProperty,
    navController: NavHostController,
    propertyViewModel: PropertyViewModel = koinViewModel()
) {
    // val propertyViewModel:PropertyViewModel = viewModel{PropertyViewModel()}
    val state by propertyViewModel.state.collectAsState()
    val onSendIntent = propertyViewModel::onIntentHandler
    val scope = rememberCoroutineScope()
    var selectedIndex by remember { mutableStateOf(0) }

    val HorizontalPagerContent: @Composable (RealEstateState) -> Unit = {realEstateState->
        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            items(items = state.realEstates.filter { if(realEstateState==RealEstateState.All) true  else  it.state==realEstateState }) {

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


    var listTab = remember {
        mutableListOf(
            HorizontalPagerItem(
                empire.digiprem.presentation.components.NavigationItem(
                    label = "All",
                    icon = Icons.Default.List,
                    onClick = {
                    },
                ),
                {
                    HorizontalPagerContent(RealEstateState.All)
                    /*PropertyALLPropertyView(
                        viewPropertyALLProperty = ViewPropertyALLProperty(),
                        navController = navController
                    )*/
                }
            ),
            HorizontalPagerItem(
                empire.digiprem.presentation.components.NavigationItem(
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




    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            ScrollableTabRow(
                containerColor = MaterialTheme.colorScheme.background,
                selectedTabIndex = pageState.currentPage,

                tabs = {
                    listTab.forEachIndexed { index, it ->
                        Tab(
                            selected = index == pageState.currentPage,
                            modifier = Modifier.padding(5.dp),
                            onClick = {
                                scope.launch {
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
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(ViewPropertyAddProperty())
                }
            ) {
                Icon(Icons.Default.AddHome, "")
            }
        }
    ) {
        HorizontalPager(
            state = pageState,
            modifier = Modifier.padding(it).fillMaxSize()
        ) { page ->
            listTab.get(page).content()
        }
    }


    //  RealEstateForm()


}

@Composable
fun RealEstateForm() {
    val typesBien = listOf("Appartement", "Maison", "Studio", "Terrain", "Local commercial")
    val typesAnnonce = listOf("Location", "Vente", "Colocation", "Location saisonnière")
    val etatsGeneraux = listOf("Neuf", "Rénové", "À rénover")
    val chauffageTypes = listOf("Électrique", "Gaz", "Collectif")
    val statutAnnonceur = listOf("Particulier", "Professionnel", "Agence")

    val selectedTypeBien = remember { mutableStateOf(typesBien.first()) }
    val selectedTypeAnnonce = remember { mutableStateOf(typesAnnonce.first()) }
    val titreAnnonce = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    val adresse = remember { mutableStateOf("") }
    val etage = remember { mutableStateOf("") }
    val latitude = remember { mutableStateOf("") }
    val longitude = remember { mutableStateOf("") }

    val surface = remember { mutableStateOf("") }
    val nbPieces = remember { mutableStateOf("") }
    val nbChambres = remember { mutableStateOf("") }
    val nbSallesBain = remember { mutableStateOf("") }
    val nbWC = remember { mutableStateOf("") }
    val meuble = remember { mutableStateOf(false) }
    val anneeConstruction = remember { mutableStateOf("") }
    val etatGeneral = remember { mutableStateOf(etatsGeneraux.first()) }

    val cuisineEquipee = remember { mutableStateOf(false) }
    val chauffage = remember { mutableStateOf(chauffageTypes.first()) }
    val climatisation = remember { mutableStateOf(false) }
    val ascenseur = remember { mutableStateOf(false) }
    val balcon = remember { mutableStateOf(false) }
    val jardin = remember { mutableStateOf(false) }
    val parking = remember { mutableStateOf(false) }
    val piscine = remember { mutableStateOf(false) }
    val fibre = remember { mutableStateOf(false) }

    val prix = remember { mutableStateOf("") }
    val chargesIncluses = remember { mutableStateOf(false) }
    val depotGarantie = remember { mutableStateOf("") }
    val fraisAgence = remember { mutableStateOf("") }

    val nomAnnonceur = remember { mutableStateOf("") }
    val telAnnonceur = remember { mutableStateOf("") }
    val emailAnnonceur = remember { mutableStateOf("") }
    val statut = remember { mutableStateOf(statutAnnonceur.first()) }

    Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {
        Text("Informations générales", style = MaterialTheme.typography.titleMedium)
        DropdownMenuField("Type de bien", typesBien, selectedTypeBien)
        DropdownMenuField("Type d'annonce", typesAnnonce, selectedTypeAnnonce)
        OutlinedTextField(
            value = titreAnnonce.value,
            onValueChange = { titreAnnonce.value = it },
            label = { Text("Titre de l’annonce") })
        OutlinedTextField(
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Description") },
            modifier = Modifier.height(120.dp)
        )

        Spacer(Modifier.height(16.dp))
        Text("📍 Localisation", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(
            value = adresse.value,
            onValueChange = { adresse.value = it },
            label = { Text("Adresse complète") })
        OutlinedTextField(value = etage.value, onValueChange = { etage.value = it }, label = { Text("Étage") })
        OutlinedTextField(value = latitude.value, onValueChange = { latitude.value = it }, label = { Text("Latitude") })
        OutlinedTextField(
            value = longitude.value,
            onValueChange = { longitude.value = it },
            label = { Text("Longitude") })

        Spacer(Modifier.height(16.dp))
        Text("🧱 Caractéristiques", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(
            value = surface.value,
            onValueChange = { surface.value = it },
            label = { Text("Surface habitable (m²)") })
        OutlinedTextField(
            value = nbPieces.value,
            onValueChange = { nbPieces.value = it },
            label = { Text("Nombre de pièces") })
        OutlinedTextField(
            value = nbChambres.value,
            onValueChange = { nbChambres.value = it },
            label = { Text("Nombre de chambres") })
        OutlinedTextField(
            value = nbSallesBain.value,
            onValueChange = { nbSallesBain.value = it },
            label = { Text("Nombre de salles de bain") })
        OutlinedTextField(value = nbWC.value, onValueChange = { nbWC.value = it }, label = { Text("Nombre de WC") })
        CheckboxWithLabel("Meublé", meuble)
        OutlinedTextField(
            value = anneeConstruction.value,
            onValueChange = { anneeConstruction.value = it },
            label = { Text("Année de construction") })
        DropdownMenuField("État général", etatsGeneraux, etatGeneral)

        Spacer(Modifier.height(16.dp))
        Text("🛏️ Équipements", style = MaterialTheme.typography.titleMedium)
        CheckboxWithLabel("Cuisine équipée", cuisineEquipee)
        DropdownMenuField("Type de chauffage", chauffageTypes, chauffage)
        CheckboxWithLabel("Climatisation", climatisation)
        CheckboxWithLabel("Ascenseur", ascenseur)
        CheckboxWithLabel("Balcon / Terrasse", balcon)
        CheckboxWithLabel("Jardin", jardin)
        CheckboxWithLabel("Parking / Garage", parking)
        CheckboxWithLabel("Piscine", piscine)
        CheckboxWithLabel("Fibre / Internet", fibre)

        Spacer(Modifier.height(16.dp))
        Text("💰 Conditions financières", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(
            value = prix.value,
            onValueChange = { prix.value = it },
            label = { Text("Prix ou loyer mensuel") })
        CheckboxWithLabel("Charges incluses", chargesIncluses)
        OutlinedTextField(
            value = depotGarantie.value,
            onValueChange = { depotGarantie.value = it },
            label = { Text("Dépôt de garantie") })
        OutlinedTextField(
            value = fraisAgence.value,
            onValueChange = { fraisAgence.value = it },
            label = { Text("Frais d’agence") })

        Spacer(Modifier.height(16.dp))
        Text("👤 Annonceur", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(
            value = nomAnnonceur.value,
            onValueChange = { nomAnnonceur.value = it },
            label = { Text("Nom / Prénom") })
        OutlinedTextField(
            value = telAnnonceur.value,
            onValueChange = { telAnnonceur.value = it },
            label = { Text("Téléphone") })
        OutlinedTextField(
            value = emailAnnonceur.value,
            onValueChange = { emailAnnonceur.value = it },
            label = { Text("Email") })
        DropdownMenuField("Statut", statutAnnonceur, statut)

        Spacer(Modifier.height(32.dp))
        Button(onClick = { /* Envoyer */ }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Soumettre l’annonce")
        }
    }
}

@Composable
fun DropdownMenuField(label: String, options: List<String>, selected: MutableState<String>) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        OutlinedTextField(
            value = selected.value,
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(text = { Text(option) }, onClick = {
                    selected.value = option
                    expanded = false
                })
            }
        }
    }
}

@Composable
fun CheckboxWithLabel(label: String, checkedState: MutableState<Boolean>) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Checkbox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
        Text(label)
    }
}
