package empire.digiprem.ui.Screen.dashboard_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier.Companion,
    authenticationNavController: NavHostController
) {

    val scrollState = rememberScrollState(0)

    Column(modifier = Modifier.fillMaxSize().verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
        RealEstateItem()
        RealEstateItem()
        RealEstateItem()
        RealEstateItem()
    }


    /* val state = rememberScrollState(0)

     val state2 = rememberScrollState(0)
     val state3 = rememberScrollState(0)
     Column(
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
                 NavigationRailWithPopupDrawer{
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
 *//*
            Box(modifier = Modifier.fillMaxWidth().align(Alignment.TopStart)) {
                TopAppBar(
                    title = {}
                )
            }*//*
        }
    }
*/

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