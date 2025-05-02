package empire.digiprem.ui.Screen.dashboard_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import empire.digiprem.config.getActualWindowsSize
import empire.digiprem.ui.component.AppVerticalScrollBar
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier.Companion,
    authenticationNavController: NavHostController
) {

    val scrollState = rememberScrollState()
    val gridState = rememberLazyGridState()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return Offset.Zero
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                scrollState.dispatchRawDelta(-consumed.y)
                return Offset.Zero
            }
        }
    }
    val scrollOffset = remember { derivedStateOf { gridState.firstVisibleItemScrollOffset } }
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(scrollState).background(Color.LightGray)
        ) {
            AppTopBar(
                modifier = Modifier.align(Alignment.TopStart)
                    /* .offset { IntOffset(x = 0, y = -scrollOffset.value / 2) }*/.zIndex(1f),
            )
            Box(
                modifier = Modifier.fillMaxWidth().height(400.dp).align(Alignment.TopStart).padding(top = 50.dp)
                    .background(Color.Blue).zIndex(1f)
            ) {
                Image(
                    painter = painterResource(Res.drawable.background_immeuble),
                    null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            /*ContentPage(modifier =
            Modifier.padding(top = 400.dp).nestedScroll(connection = nestedScrollConnection) // Parallax ou suivi partiel
                .align(Alignment.Center)
                .zIndex(0f),
            state = gridState,
        )*/
            AppVerticalScrollBar(
                modifier = Modifier.align(Alignment.TopEnd).zIndex(1f),
                lazyGridState = gridState
            )
        }
    }

    val state = rememberScrollState(0)

    val state2 = rememberScrollState(0)
    val state3 = rememberScrollState(0)
/*    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
    ) {
        Box(modifier = Modifier.fillMaxSize().verticalScroll(state).background(Color.LightGray.copy(alpha = 0.3f))) {

            androidx.compose.animation.AnimatedVisibility(state3.value > 3) {
                Box(
                    modifier = Modifier.padding(top = 100.dp, start = 20.dp).height(300.dp).width(120.dp)
                        .clip(RoundedCornerShape(10.dp)).align(Alignment.TopStart)
                ) {
                    Column(
                        modifier = Modifier.height(300.dp).width(120.dp).background(Color.Red)
                            .align(Alignment.CenterStart).verticalScroll(state2),
                        verticalArrangement = Arrangement.spacedBy(50.dp)
                    ) {
                        Text(
                            text = "Bonjour le monde"
                        )
                        Text(
                            text = "Bonjour le monde"
                        )
                        Text(
                            text = "Bonjour le monde"
                        )
                        Text(
                            text = "Bonjour le monde"
                        )
                        Text(
                            text = "Bonjour le monde"
                        )
                        Text(
                            text = "Bonjour le monde"
                        )
                        Text(
                            text = "Bonjour le monde"
                        )
                        Text(
                            text = "Bonjour le monde"
                        )
                        Text(
                            text = "Bonjour le monde"
                        )
                    }
                    AppVerticalScrollBar(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        scrollState = state2,
                    )
                }
            }
            Box(
                modifier = Modifier.matchParentSize(),
            ) {
                NavigationRailWithPopupDrawer {
                    Box(
                        modifier = Modifier.padding(top = 60.dp).width(1000.dp)
                            .align(Alignment.Center)
                    ) {
                        Column(
                            modifier = Modifier.background(Color.White).fillMaxWidth().align(Alignment.CenterStart)
                                .verticalScroll(state3).padding(20.dp),

                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(50.dp)
                        ) {
                            Title("Bienvenue sur le blog Jetpack Compose")
                            Subtitle("Introduction")
                            Paragraph("Jetpack Compose est un framework moderne de développement d'interface utilisateur pour Android et le bureau.")

                            Subtitle("Avantages")
                            BulletList(
                                items = listOf(
                                    "Déclaration UI avec Kotlin",
                                    "Prévisualisation en direct",
                                    "Moins de code boilerplate",
                                    "Facile à tester et à maintenir"
                                )
                            )

                            Subtitle("Exemple de code")
                            CodeBlock(
                                """
                     @Composable
                     fun Greeting(name: String) {
                         Text("Hello ")
                     }
                 """.trimIndent()
                            )
                            Subtitle("Conclusion")
                            Paragraph("Compose simplifie le développement d’interfaces riches, réactives et élégantes.")
                        }
                        AppVerticalScrollBar(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            scrollState = state3,
                        )
                    }
                }
            }

            Box(modifier = Modifier.fillMaxWidth().align(Alignment.TopStart)) {
                TopAppBar(
                    title = {}
                )
            }
        }
    }*/

}

@Composable
fun ContentPage(modifier: Modifier = Modifier, state: LazyGridState = rememberLazyGridState()) {
    val count = when (getActualWindowsSize().widthSizeClass) {
        WindowWidthSizeClass.Expanded -> {
            3
        }

        WindowWidthSizeClass.Medium -> {
            2
        }

        else -> {
            1
        }
    }
    LazyVerticalGrid(
        modifier = Modifier.then(modifier).height(800.dp).width(1000.dp),
        state = state,
        columns = GridCells.Fixed(count),
        userScrollEnabled = false,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(20) {
            RealEstateItem2()
        }
    }
}

@Composable
fun AppTopBar(modifier: Modifier) {
    Box(
        modifier = Modifier.then(modifier)
            .fillMaxWidth()
            .background(Color(0xFF1E88E5))
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text("Documentation Compose", color = Color.White, style = MaterialTheme.typography.bodyMedium)
    }
}


@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E88E5))
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text("Documentation Compose", color = Color.White, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun Sidebar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(Color(0xFFE0E0E0)).padding(12.dp)
    ) {
        Text("Navigation", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Accueil")
        Text("Guide")
        Text("Référence")
    }
}

@Composable
fun Title(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium, color = Color(0xFF212121))
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun Subtitle(text: String) {
    Text(text, style = MaterialTheme.typography.titleSmall, color = Color(0xFF424242))
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun Paragraph(text: String) {
    Text(text, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF616161))
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun BulletList(items: List<String>) {
    Column {
        items.forEach {
            Row {
                Text("•", modifier = Modifier.padding(end = 8.dp))
                Text(it)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CodeBlock(code: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEEEEEE))
            .padding(12.dp)
    ) {
        Text(code, style = MaterialTheme.typography.titleMedium, color = Color(0xFF37474F))
    }
}