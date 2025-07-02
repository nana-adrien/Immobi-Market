package empire.digiprem.presentation.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import empire.digiprem.config.isCompactApplication
import empire.digiprem.config.isMediumMobilePlatform
import empire.digiprem.enums.Role
import empire.digiprem.enums.TypeDHabitation
import empire.digiprem.enums.TypeDeBien
import empire.digiprem.enums.TypeDoffre
import empire.digiprem.navigation.EnumView
import empire.digiprem.navigation.ViewPropertyAddProperty
import empire.digiprem.presentation.base.AppAnimations.slideInStart
import empire.digiprem.presentation.base.AppAnimations.slideOutStart
import empire.digiprem.presentation.components.AppScrollableDialog
import empire.digiprem.presentation.components.AppTextField
import empire.digiprem.presentation.components.AppVerticalScrollBar
import empire.digiprem.presentation.components.app.RealEstateItem2
import empire.digiprem.presentation.components.app.RealEstateType
import empire.digiprem.presentation.components.app.equipmentes
import empire.digiprem.presentation.components.app.generateFakeRealEstateListCameroon
import empire.digiprem.presentation.components.forms.*
import empire.digiprem.presentation.components.wrapper.PageWrapperState
import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
import empire.digiprem.presentation.intents.HomeIntent
import empire.digiprem.presentation.viewmodels.PropertyAddPropertyViewModel
import empire.digiprem.presentation.views.Authentication.FormErrorMessageSection
import empire.digiprem.ui.Screen.DetailRealEstateItemScreen
import empire.digiprem.ui.Screen.dashboard_screen.PageSection
import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.BienImmobilierViewModel
import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.Etape1_InfoGenerales
import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.Etape2_Localisation
import kotlinx.coroutines.launch
import octopusfx.client.mobile.core.ui.theme.LocalSessionManager
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyAddPropertyView(
    viewPropertyAddProperty: ViewPropertyAddProperty,
    navController: NavHostController,
    addpropertyViewModel: PropertyAddPropertyViewModel = koinViewModel()
) {
    val utilisateur by LocalSessionManager.current.utilisateur.collectAsState()
    val state by addpropertyViewModel.state.collectAsState()
    val onSendIntent = addpropertyViewModel::onIntentHandler
    val scrollState = rememberScrollState(0)
    var enabledTypeDoffreMenu by remember { mutableStateOf(false) }
    var enabledTypeDeBienMenu by remember { mutableStateOf(false) }
    var typeDeBien: String by remember { mutableStateOf(TypeDeBien.NONE.name) }
    var typeDoffre: String by remember { mutableStateOf(TypeDoffre.NONE.name) }
    val typeDHabitation: TypeDHabitation? by remember { mutableStateOf(null) }
    WebDesktopPageWrapper(
        modifier = Modifier,
        topBarModifier = Modifier.wrapContentHeight(),
        view = EnumView.ViewPropertyAddProperty,
        actions = {
            Row(modifier = Modifier.wrapContentSize().padding(vertical = 8.dp).padding(end=20.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                AppOutinedButtonEx(
                    onClick = {}
                ) {
                    Text("Annuler", color = MaterialTheme.colorScheme.onBackground)
                }
                AppButtonEx(onClick = {}) {
                    Text("Ajouter", color = MaterialTheme.colorScheme.background)
                }
            }
        },
        state = PageWrapperState(isSuccess = true)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(30.dp),){
            FormErrorMessageSection(
                enabled = true,
                errorMessage = "message d'erreur"
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                item {
                    ColumRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        FormContentWrapper(
                            "Entrer"
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Section("Titre de l'offre") {
                                    AppTextField(
                                        value = "",
                                        onValueChange = {}
                                    )
                                }
                                Row(
                                    modifier = Modifier.wrapContentHeight(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    Box(modifier = Modifier.weight(1f)) {
                                        Section("Type d'offre") {
                                            SelectContentTextField(
                                                enabledMenu = enabledTypeDoffreMenu,
                                                onDismissRequest = {enabledTypeDoffreMenu=!enabledTypeDoffreMenu},
                                                selected = typeDoffre,
                                                options = TypeDoffre.entries.map { it.name },
                                                onValueChange = { typeDoffre=it}
                                            )
                                        }
                                    }
                                    Box(modifier = Modifier.weight(1f)) {
                                        Section("type de bien") {
                                            SelectContentTextField(
                                                enabledMenu = enabledTypeDeBienMenu,
                                                onDismissRequest = {enabledTypeDeBienMenu=!enabledTypeDeBienMenu},
                                                selected = typeDeBien,
                                                options = TypeDeBien.entries.map { it.name },
                                                onValueChange = { typeDeBien=it}
                                            )
                                        }

                                    }
                                }
                                Row(
                                    modifier = Modifier.wrapContentHeight(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    Box(modifier = Modifier.weight(1f)) {
                                        Section("Superficie en m2") {
                                            AppTextField(
                                                value = "",
                                                onValueChange = {},
                                                trailingIcon = {Text("/m2")}
                                            )
                                        }
                                    }
                                    Box(modifier = Modifier.weight(1f)) {
                                        Section("Prix en Fcfa") {
                                            AppTextField(
                                                value = "",
                                                onValueChange = {},
                                                trailingIcon = {Text("/Mois")}
                                            )
                                        }
                                    }
                                }

                                Section(
                                    title = "Description du bien",
                                ) {
                                    AppTextField(
                                        modifier = Modifier.height(150.dp),
                                        value = "",
                                        onValueChange = {}
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    var typeDHabitation by remember { mutableStateOf(TypeDHabitation.MODERNE.name) }
                    typeDeBien?.let {
                        FormContentWrapper(
                            "Caracteristiques"
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Section(
                                    title = "Type de $it",
                                ) {
                                    RadioTextField(
                                        label = "Selectionner un type",
                                        selectedOption = typeDHabitation,
                                        onSelectOption = {
                                            typeDHabitation = it
                                        },
                                        option = TypeDHabitation.entries.filter { it != TypeDHabitation.NONE }
                                            .map { e -> e.name }
                                    )
                                }

                                when (it) {
                                    TypeDeBien.CHAMBRE.name -> {
                                        if (typeDHabitation == TypeDHabitation.MODERNE.name || typeDHabitation == TypeDHabitation.MEUBLER.name) {

                                            CheckTextField(
                                                label = "Avec Douche",
                                                value = false,
                                                onValueChange = {}
                                            )
                                            CheckTextField(
                                                label = "Avec un petit espace de cuisine",
                                                value = false,
                                                onValueChange = {}
                                            )
                                        }
                                    }

                                    TypeDeBien.MAISON.name -> {
                                        IncrementeDecrementValue(
                                            label = "Nombre de chambre",
                                            value = 0,
                                            onValueChange = {}
                                        )
                                        if (typeDHabitation == TypeDHabitation.MODERNE.name || typeDHabitation == TypeDHabitation.MEUBLER.name) {
                                            IncrementeDecrementValue(
                                                label = "Nombre de sale de bain ",
                                                value = 0,
                                                onValueChange = {}
                                            )
                                        }
                                        IncrementeDecrementValue(
                                            label = "Nombre de salon",
                                            value = 0,
                                            onValueChange = {}
                                        )
                                        CheckTextField(
                                            label = "chambre avec Douche",
                                            value = false,
                                            onValueChange = {}
                                        )
                                    }

                                    else -> {
                                        Text("Aucune caracteristique assossier a se bien ")
                                    }
                                }
                            }
                        }
                    }
                }
                item {
                    ColumRow {
                        var images by remember { mutableStateOf(listOf<Any>()) }
                        var selectedImage by remember { mutableStateOf<Any?>(null) }

                        FormContentWrapper(
                            title = "Ajouter des immages "
                        ) {
                            Box(Modifier) {
                                FormSelectImages(
                                    selectedImage = selectedImage,
                                    images = images,
                                    onSelectImages = { images += it },
                                    onSelectedImageChange = {
                                        selectedImage = it
                                    },
                                    onRemoveImage = {
                                        it?.let { images -= it }
                                    }

                                )
                            }
                        }
                    }
                }

                item {
                    ColumRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(modifier = Modifier) {
                            AddRealStateFormItem1()
                        }
                        Box(modifier = Modifier) {
                            AddRealStateFormItem2()
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun AbonnementRequisCard(
    titre: String = "🔒 Abonnement requis",
    message: String = "Pour publier un bien, vous devez disposer d’un abonnement actif. Abonnez-vous maintenant pour profiter de tous les avantages.",
    onAbonnerClick: () -> Unit,
    onAnnulerClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(600.dp).width(400.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(7.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = titre,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AppButtonEx(
                    onClick = onAbonnerClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("S’abonner")
                }

                OutlinedButton(
                    onClick = onAnnulerClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Annuler")
                }
            }
        }
    }
}


@Composable
fun AddRealStateForm() {
    Column {
        /* LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
*/
        //   item {
        ColumRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AddRealStateFormItem1()
            AddRealStateFormItem2()
        }

        //   }
        //   item {
        ColumRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                AddRealStateFormItem3()
            }
            Box(modifier = Modifier.weight(1f)) {
                AddRealStateFormItem5()
            }

        }
        //   }
        // item {
        AddRealStateFormItem4()
        //  }
        //   item {
        Spacer(Modifier.height(20.dp))
        //   }


        // }
    }
}


@Composable
fun ColumRow(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable () -> Unit = {}
) {
    if (isMediumMobilePlatform() || isCompactApplication()) {
        Column(
            modifier = modifier,
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalArrangement,
            content = { content() }
        )
    } else {
        Row(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
            content = { content() }
        )


    }

}


@Composable
fun AddRealStateFormItem1() {
    var selectOption by remember { mutableStateOf("bonjour") }
    var selectOption2 by remember { mutableStateOf("bonjour") }
    var value by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("0.0") }
    var value3 by remember { mutableStateOf("0.0") }
    var enableMenuOption by remember { mutableStateOf(false) }
    var enableMenuOption2 by remember { mutableStateOf(false) }


    FormContentWrapper("Texte 1") {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AppTextField(
                label = { Text("Titre de l'annonce") },
                value = value,
                onValueChange = { value = it }
            )
            Row(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    SelectContentTextField(
                        enabledMenu = enableMenuOption2,
                        onDismissRequest = { enableMenuOption2 = !enableMenuOption2 },
                        selected = selectOption2,
                        options = listOf("bonjour", "le", "monde"),
                        onValueChange = { selectOption2 = it }
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    SelectContentTextField(
                        enabledMenu = enableMenuOption,
                        onDismissRequest = { enableMenuOption = !enableMenuOption },
                        selected = selectOption,
                        options = listOf("bonjour", "le", "monde"),
                        onValueChange = { selectOption = it }
                    )
                }


            }
            AppTextField(
                label = { Text("Montant en FCFA") },
                trailingIcon = { Text("FCFA") },
                value = value3,
                onValueChange = { value3 = it }
            )
            EdithTextField(
                label = "Superficie en m2",
                value = value2,
                onValueChange = { value2 = it },
                trailingIcon = { Text("m2") },
            )
            EdithTextField(
                label = "Superficie en m2",
                enabledModification = false,
                value = value2,
                onValueChange = { value2 = it },
                trailingIcon = { Text("m2") },
            )
        }
    }
}

@Composable
fun AddRealStateFormItem2() {
    var value1 by remember { mutableStateOf(0) }
    var value2 by remember { mutableStateOf(0) }
    var value3 by remember { mutableStateOf(0) }
    FormContentWrapper(
        title = "caracteristiquue"
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            IncrementeDecrementValue(
                label = "nombre de pieces", value1, enabledModification = false,
            ) {
                value1 = it

            }
            IncrementeDecrementValue(

                label = "nombre de pieces", value1
            ) {
                value1 = it

            }
            IncrementeDecrementValue(label = "nombre de chambre", value2) {
                value2 = it

            }
            IncrementeDecrementValue(label = "nombre de pieces", value2, enabledModification = false) {
                value2 = it

            }
            IncrementeDecrementValue(label = " nombree de salon ", value3) {
                value3 = it
            }
        }
    }
}

@Composable
fun AddRealStateFormItem3() {
    var value1 by remember { mutableStateOf(false) }
    var value2 by remember { mutableStateOf(false) }
    var value3 by remember { mutableStateOf(false) }
    var selectOption by remember { mutableStateOf("bonjour") }
    FormContentWrapper(
        title = "Equipement"
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SwitchTextField(
                enabled = true,
                label = "Add Real State",
                value = value1,
                onValueChange = { value1 = it }
            )
            CheckTextField(
                enabled = true,
                label = "Add Real State",
                value = value3,
                onValueChange = { value3 = it }
            )
            CheckTextField(
                enabled = true,
                label = "Add Real State",
                value = value3,
                onValueChange = { value3 = it }
            )
            SwitchTextField(
                enabled = true,
                label = "Add Real State",
                value = value2,
                onValueChange = { value2 = it }
            )
            SwitchTextField(
                enabled = true,
                label = "Add Real State",
                value = value2,
                enabledModification = false,
                onValueChange = { value2 = it }
            )
            RadioTextField(
                enabled = true,
                label = "select",
                selectedOption = selectOption,
                option = listOf("bonjour", "le", "monde"),
                onSelectOption = { selectOption = it },
            )
            RadioTextField(
                enabled = true,
                label = "select",
                enabledModification = false,
                selectedOption = selectOption,
                option = listOf("bonjour", "le", "monde"),
                onSelectOption = { selectOption = it },
            )
        }
    }
}

@Composable
fun AddRealStateFormItem4() {
    var selectedImage by remember { mutableStateOf<Any?>(null) }
    var images by remember { mutableStateOf<MutableList<Any>>(mutableListOf(Res.drawable.background_immeuble)) }

    FormContentWrapper(
        title = "Images"
    ) {
        FormSelectImages(
            selectedImage = selectedImage,
            images = images,
            onSelectImages = {
                images = (images + it.toMutableList()).toMutableList()
            },
            onRemoveImage = {
                it?.let {
                    images = (images - it).toMutableList()
                }
            },
            onSelectedImageChange = {
                selectedImage = it
            }
        )
    }
}

@Composable
fun AddRealStateFormItem5() {

    var selectedOption by remember { mutableStateOf(listOf("MAISON")) }
    FormContentWrapper(
        title = "Equipement"
    ) {
        SelectMultipOptions(
            options = listOf(
                Option("MAI1SON7", "Maison"),
                Option("1CHAMBRE", "Chambre"),
                Option("APPAR1TEMENT", "Appartement"),
                Option("MAI1SONr", "Maison"),
                Option("CHA1MBRrE", "Chambre"),
                Option("APPA1EMaENT", "Appartement"),
                Option("MAISONa", "Maison"),
                Option("CHAMBc1RE", "Chambre"),
                Option("APPARTHE1MENT", "Appartement"),
                Option("M1A4ISONH", "Maison"),
                Option("CHA8MB4RE", "Chambre"),
                Option("APPAR1TEMENT", "Appartement"),
                Option("M1AIS7ON", "Maison"),
                Option("CHAM1BRE", "Chambre"),
                Option("APPA7RTE1MENT", "Appartement"),
                Option("MAI4SON1", "Maison"),
                Option("CHAM15BRE", "Chambre"),
                Option("AP1PAR8TEMENT", "Appartement"),
                Option("MAI8SON", "Maison"),
                Option("CH8AMBR1E", "Chambre"),
                Option("APPA1RTEM8ENT", "Appartement"),
            ),
            selectedOptions = selectedOption,
            onSelectOption = {
                if (selectedOption.contains(it)) {
                    selectedOption -= it
                } else {
                    selectedOption += it
                }
            }
        )
    }
}


data class Stape(
    val title: String,
    val hasCompleted: Boolean = false,
    val content: @Composable () -> Unit
)

@Composable
private fun StapeComponent(
    currentStape: Int,
    stapes: List<Stape>,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().width(500.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        stapes.forEachIndexed { index, stape ->
            var textColor = Color.White
            val color = if (index == currentStape) {
                textColor =
                    Color.White; Color.Blue
            } else {
                if (stape.hasCompleted) Color.Green else {
                    textColor =
                        Color.Black;Color.Gray.copy(
                        alpha = 0.5f
                    )
                }
            }
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (index != 0) {
                    HorizontalDivider(
                        modifier = Modifier.width(
                            40.dp
                        ).height(5.dp).background(color)
                    )
                }
                Box(
                    modifier = Modifier.size(30.dp)
                        .clip(CircleShape).background(color)
                        .clickable(enabled = stape.hasCompleted) { onClick(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Text("${index + 1}", color = textColor)
                }
            }
        }
    }
}
