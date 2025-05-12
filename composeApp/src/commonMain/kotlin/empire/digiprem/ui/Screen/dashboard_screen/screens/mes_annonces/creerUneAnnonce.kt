package empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import empire.digiprem.presentation.components.AppVerticalScrollBar
import kotlinx.coroutines.launch

@Composable
fun CreerUneAnnonce() {
    val scrollState =rememberScrollState(0)
    Box(modifier = Modifier.fillMaxSize().background(Color.Blue)) {
        Box(modifier = Modifier.fillMaxSize()
                .background(Color.White.copy(alpha = 0.9f))
                .verticalScroll(scrollState), contentAlignment = _root_ide_package_.androidx.compose.ui.Alignment.Center) {
            var stapes: List<Stape> by _root_ide_package_.androidx.compose.runtime.remember {
                _root_ide_package_.androidx.compose.runtime.mutableStateOf(
                    listOf(
                        Stape(title = "bonjour") { Etape1_InfoGenerales() },
                        Stape(title = "bonsoir") { Etape2_Localisation(BienImmobilierViewModel()) },
                        Stape(title = "apres") { Etape1_InfoGenerales() },
                        Stape(title = "encore") { Etape1_InfoGenerales() },
                        Stape(title = "tout") { Etape1_InfoGenerales() },
                        Stape(title = "toutf") { Etape1_InfoGenerales() },
                    )
                )
            }
            val pagerState =rememberPagerState(0) { stapes.size }
            var currentStape: Int by remember {
                   mutableStateOf(
                        0
                    )
                }
            val scope = _root_ide_package_.androidx.compose.runtime.remember {
                _root_ide_package_.kotlinx.coroutines.CoroutineScope(_root_ide_package_.kotlinx.coroutines.Dispatchers.Main)
            }

            _root_ide_package_.androidx.compose.foundation.layout.Column(
                modifier = _root_ide_package_.androidx.compose.ui.Modifier.padding(30.dp).width(900.dp).height(600.dp),
                horizontalAlignment = _root_ide_package_.androidx.compose.ui.Alignment.CenterHorizontally,
                verticalArrangement = _root_ide_package_.androidx.compose.foundation.layout.Arrangement.spacedBy(25.dp)
            ) {
                _root_ide_package_.androidx.compose.material3.Text(
                    text = if (currentStape <= stapes.size - 1) stapes[currentStape].title else "Terminer",
                    style = _root_ide_package_.androidx.compose.material3.MaterialTheme.typography.titleLarge.copy(
                        fontSize = 25.sp,
                        fontWeight = _root_ide_package_.androidx.compose.ui.text.font.FontWeight.Bold
                    )
                )
                StapeComponent(
                    currentStape = currentStape,
                    stapes = stapes,
                    onClick = {
                        scope.launch {
                            currentStape = it
                            pagerState.scrollToPage(currentStape)
                        }
                    }
                )
                _root_ide_package_.androidx.compose.foundation.pager.HorizontalPager(
                    modifier = _root_ide_package_.androidx.compose.ui.Modifier
                        .fillMaxSize(),
                    state = pagerState
                ) {
                    _root_ide_package_.androidx.compose.foundation.layout.Column(
                        modifier = _root_ide_package_.androidx.compose.ui.Modifier.wrapContentSize(),
                        verticalArrangement = _root_ide_package_.androidx.compose.foundation.layout.Arrangement.spacedBy(
                            15.dp
                        )
                    ) {
                        _root_ide_package_.androidx.compose.material3.Card(
                            modifier = _root_ide_package_.androidx.compose.ui.Modifier.fillMaxWidth(),
                            elevation = _root_ide_package_.androidx.compose.material3.CardDefaults.cardElevation(4.dp)
                        ) {
                            stapes.get(it).content()
                        }
                        _root_ide_package_.androidx.compose.foundation.layout.Row(
                            horizontalArrangement = _root_ide_package_.androidx.compose.foundation.layout.Arrangement.SpaceBetween,
                            modifier = _root_ide_package_.androidx.compose.ui.Modifier.fillMaxWidth()
                                .padding(top = 16.dp),
                        ) {
                            Box(
                                modifier = _root_ide_package_.androidx.compose.ui.Modifier.weight(
                                    1f
                                ), contentAlignment = _root_ide_package_.androidx.compose.ui.Alignment.CenterStart
                            ) {
                                if (currentStape > 0) {
                                    _root_ide_package_.androidx.compose.material3.Button(
                                        modifier = _root_ide_package_.androidx.compose.ui.Modifier.align(
                                            _root_ide_package_.androidx.compose.ui.Alignment.CenterStart
                                        ),
                                        onClick = {
                                            scope.launch {
                                                stapes = stapes.mapIndexed { index, state ->
                                                    if (index == currentStape) {
                                                        state.copy(hasCompleted = false)
                                                    } else {
                                                        state
                                                    }
                                                }
                                                currentStape--
                                                pagerState.scrollToPage(currentStape)
                                            }


                                        }) {
                                        _root_ide_package_.androidx.compose.material3.Text("Précédent")
                                    }
                                }
                            }
                            Box(
                                modifier = _root_ide_package_.androidx.compose.ui.Modifier.weight(
                                    1f
                                ), contentAlignment = _root_ide_package_.androidx.compose.ui.Alignment.CenterEnd
                            ) {
                                if (currentStape < stapes.size) {
                                    _root_ide_package_.androidx.compose.material3.Button(onClick = {
                                        scope.launch {
                                            stapes = stapes.mapIndexed { index, state ->
                                                if (index == currentStape) {
                                                    state.copy(hasCompleted = true)
                                                } else {
                                                    state
                                                }
                                            }
                                            currentStape++
                                            pagerState.scrollToPage(currentStape)
                                        }
                                    }) {
                                        _root_ide_package_.androidx.compose.material3.Text("Suivant")
                                    }
                                } else {
                                    _root_ide_package_.androidx.compose.material3.Button(
                                        onClick = {
                                            // TODO: Envoyer les données
                                        }) {
                                        _root_ide_package_.androidx.compose.material3.Text("Soumettre")
                                    }
                                }
                            }


                        }
                    }
                }
            }


        }
        AppVerticalScrollBar(
            modifier = Modifier.align(Alignment.CenterEnd),
            scrollState = scrollState,
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
    _root_ide_package_.androidx.compose.foundation.layout.Row(
        modifier = _root_ide_package_.androidx.compose.ui.Modifier.fillMaxWidth().width(500.dp),
        verticalAlignment = _root_ide_package_.androidx.compose.ui.Alignment.CenterVertically,
        horizontalArrangement = _root_ide_package_.androidx.compose.foundation.layout.Arrangement.Center
    ) {
        stapes.forEachIndexed { index, stape ->
            var textColor = _root_ide_package_.androidx.compose.ui.graphics.Color.White
            val color = if (index == currentStape) {
                textColor =
                    _root_ide_package_.androidx.compose.ui.graphics.Color.White; _root_ide_package_.androidx.compose.ui.graphics.Color.Blue
            } else {
                if (stape.hasCompleted) _root_ide_package_.androidx.compose.ui.graphics.Color.Green else {
                    textColor =
                        _root_ide_package_.androidx.compose.ui.graphics.Color.Black; _root_ide_package_.androidx.compose.ui.graphics.Color.Gray.copy(
                        alpha = 0.5f
                    )
                }
            }
            _root_ide_package_.androidx.compose.foundation.layout.Row(
                modifier = _root_ide_package_.androidx.compose.ui.Modifier.wrapContentSize(),
                verticalAlignment = _root_ide_package_.androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = _root_ide_package_.androidx.compose.foundation.layout.Arrangement.Center
            ) {
                if (index != 0) {
                    _root_ide_package_.androidx.compose.material3.HorizontalDivider(
                        modifier = _root_ide_package_.androidx.compose.ui.Modifier.width(
                            80.dp
                        ).height(5.dp).background(color)
                    )
                }
                Box(
                    modifier = _root_ide_package_.androidx.compose.ui.Modifier.size(50.dp)
                        .clip(_root_ide_package_.androidx.compose.foundation.shape.CircleShape).background(color)
                        .clickable(enabled = stape.hasCompleted) { onClick(index) },
                    contentAlignment = _root_ide_package_.androidx.compose.ui.Alignment.Center
                ) {
                    _root_ide_package_.androidx.compose.material3.Text("${index + 1}", color = textColor)
                }
            }
        }
    }
}

@Composable
fun FormulairePublication() {
    var currentStep by _root_ide_package_.androidx.compose.runtime.remember {
        _root_ide_package_.androidx.compose.runtime.mutableStateOf(
            0
        )
    }

    _root_ide_package_.androidx.compose.foundation.layout.Column(
        modifier = _root_ide_package_.androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (currentStep) {
            0 -> Etape1_InfoGenerales()
            1 -> Etape2_Localisation(BienImmobilierViewModel())
            2 -> Etape1_InfoGenerales()
            3 -> Etape1_InfoGenerales()
            4 -> Etape1_InfoGenerales()
            5 -> Etape1_InfoGenerales()
            6 -> Etape1_InfoGenerales()
        }

        _root_ide_package_.androidx.compose.foundation.layout.Row(
            horizontalArrangement = _root_ide_package_.androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            modifier = _root_ide_package_.androidx.compose.ui.Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            if (currentStep > 0) {
                _root_ide_package_.androidx.compose.material3.Button(onClick = { currentStep-- }) {
                    _root_ide_package_.androidx.compose.material3.Text("Précédent")
                }
            }
            if (currentStep < 6) {
                _root_ide_package_.androidx.compose.material3.Button(onClick = { currentStep++ }) {
                    _root_ide_package_.androidx.compose.material3.Text("Suivant")
                }
            } else {
                _root_ide_package_.androidx.compose.material3.Button(onClick = {
                    // TODO: Envoyer les données
                }) {
                    _root_ide_package_.androidx.compose.material3.Text("Soumettre")
                }
            }
        }
    }
}

@Composable
fun Etape1_InfoGenerales() {
    _root_ide_package_.androidx.compose.foundation.layout.Column(
        modifier = _root_ide_package_.androidx.compose.ui.Modifier.padding(
            16.dp
        )
    ) {
        _root_ide_package_.androidx.compose.material3.Text(
            "1. Informations générales",
            style = _root_ide_package_.androidx.compose.material3.MaterialTheme.typography.titleMedium
        )
        _root_ide_package_.androidx.compose.foundation.layout.Spacer(
            modifier = _root_ide_package_.androidx.compose.ui.Modifier.height(
                8.dp
            )
        )

        var typeBien by _root_ide_package_.androidx.compose.runtime.remember {
            _root_ide_package_.androidx.compose.runtime.mutableStateOf(
                ""
            )
        }
        var typeAnnonce by _root_ide_package_.androidx.compose.runtime.remember {
            _root_ide_package_.androidx.compose.runtime.mutableStateOf(
                ""
            )
        }
        var titre by _root_ide_package_.androidx.compose.runtime.remember {
            _root_ide_package_.androidx.compose.runtime.mutableStateOf(
                ""
            )
        }
        var description by _root_ide_package_.androidx.compose.runtime.remember {
            _root_ide_package_.androidx.compose.runtime.mutableStateOf(
                ""
            )
        }

        _root_ide_package_.androidx.compose.material3.OutlinedTextField(
            value = typeBien,
            onValueChange = { typeBien = it },
            label = { _root_ide_package_.androidx.compose.material3.Text("Type de bien") })
        _root_ide_package_.androidx.compose.material3.OutlinedTextField(
            value = typeAnnonce,
            onValueChange = { typeAnnonce = it },
            label = { _root_ide_package_.androidx.compose.material3.Text("Type d’annonce") })
        _root_ide_package_.androidx.compose.material3.OutlinedTextField(
            value = titre,
            onValueChange = { titre = it },
            label = { _root_ide_package_.androidx.compose.material3.Text("Titre de l’annonce") })
        _root_ide_package_.androidx.compose.material3.OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { _root_ide_package_.androidx.compose.material3.Text("Description") },
            modifier = _root_ide_package_.androidx.compose.ui.Modifier.height(120.dp)
        )
    }
}

// ---------- Data Model ----------
data class BienImmobilierForm(
    var typeBien: String = "",
    var typeAnnonce: String = "",
    var titre: String = "",
    var description: String = "",
    var adresse: String = "",
    var ville: String = "",
    var codePostal: String = "",
    var superficie: String = "",
    var nbPieces: String = "",
    var nbChambres: String = "",
    var nbSallesDeBain: String = "",
    var hasCuisine: Boolean = false,
    var hasParking: Boolean = false,
    var hasBalcon: Boolean = false,
    var hasJardin: Boolean = false,
    var images: List<String> = emptyList(),
    var prix: String = "",
    var charges: String = "",
    var nomProprietaire: String = "",
    var telephone: String = "",
    var email: String = ""
)

// ---------- ViewModel ----------
class BienImmobilierViewModel : _root_ide_package_.androidx.lifecycle.ViewModel() {
    var formUiState by _root_ide_package_.androidx.compose.runtime.mutableStateOf(BienImmobilierForm())
        private set

    fun updateForm(update: BienImmobilierForm.() -> Unit) {
        formUiState = formUiState.copy().apply(update)
    }
}

// ---------- Exemple d'étape 2 (localisation) ----------
@Composable
fun Etape2_Localisation(viewModel: BienImmobilierViewModel) {
    val form = viewModel.formUiState
    _root_ide_package_.androidx.compose.foundation.layout.Column(
        modifier = _root_ide_package_.androidx.compose.ui.Modifier.padding(
            16.dp
        )
    ) {
        _root_ide_package_.androidx.compose.material3.Text(
            "2. Localisation",
            style = _root_ide_package_.androidx.compose.material3.MaterialTheme.typography.titleMedium
        )
        _root_ide_package_.androidx.compose.foundation.layout.Spacer(
            modifier = _root_ide_package_.androidx.compose.ui.Modifier.height(
                8.dp
            )
        )

        _root_ide_package_.androidx.compose.material3.OutlinedTextField(
            value = form.adresse,
            onValueChange = { viewModel.updateForm { adresse = it } },
            label = { _root_ide_package_.androidx.compose.material3.Text("Adresse") }
        )

        _root_ide_package_.androidx.compose.material3.OutlinedTextField(
            value = form.ville,
            onValueChange = { viewModel.updateForm { ville = it } },
            label = { _root_ide_package_.androidx.compose.material3.Text("Ville") }
        )

        _root_ide_package_.androidx.compose.material3.OutlinedTextField(
            value = form.codePostal,
            onValueChange = { viewModel.updateForm { codePostal = it } },
            label = { _root_ide_package_.androidx.compose.material3.Text("Code Postal") }
        )
    }
}
