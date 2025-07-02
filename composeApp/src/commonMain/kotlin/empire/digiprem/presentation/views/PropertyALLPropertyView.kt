 package empire.digiprem.presentation.views

   import androidx.compose.foundation.layout.*
   import androidx.compose.foundation.lazy.LazyColumn
   import androidx.compose.foundation.pager.HorizontalPager
   import androidx.compose.foundation.pager.rememberPagerState
   import androidx.compose.foundation.rememberScrollState
   import androidx.compose.foundation.verticalScroll
   import androidx.compose.material.icons.Icons
   import androidx.compose.material.icons.filled.Upload
   import androidx.compose.material3.*
   import androidx.compose.runtime.*
   import androidx.compose.ui.Alignment
   import empire.digiprem.navigation.ViewPropertyALLProperty
   import androidx.lifecycle.viewmodel.compose.viewModel
   import androidx.compose.ui.Modifier
   import androidx.compose.ui.graphics.Color
   import androidx.compose.ui.text.font.FontWeight
   import androidx.compose.ui.unit.dp
   import androidx.compose.ui.unit.sp
   import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
   import coil3.Uri
   import empire.digiprem.navigation.EnumView
   import empire.digiprem.presentation.components.AppVerticalScrollBar
   import empire.digiprem.presentation.components.forms.FormTextFieldWrapper
   import empire.digiprem.presentation.components.wrapper.PageWrapperState
   import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
   import empire.digiprem.presentation.viewmodels.PropertyALLPropertyViewModel
import empire.digiprem.presentation.intents.PropertyALLPropertyIntent
   import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.BienImmobilierViewModel
   import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.Etape1_InfoGenerales
   import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.Etape2_Localisation
   import kotlinx.coroutines.launch

 @OptIn(ExperimentalMaterial3Api::class)
 @Composable
 fun PropertyALLPropertyView(
 viewPropertyALLProperty:ViewPropertyALLProperty,
 navController: NavHostController,
 allpropertyViewModel:PropertyALLPropertyViewModel = koinViewModel()
 ) {
        // val allpropertyViewModel:PropertyALLPropertyViewModel = viewModel{PropertyALLPropertyViewModel()}
         val state by allpropertyViewModel.state.collectAsState()
         val onSendIntent=allpropertyViewModel::onIntentHandler
     WebDesktopPageWrapper(
         modifier = Modifier,
         view = EnumView.ViewPropertyALLProperty,
         state = PageWrapperState(isSuccess = true)
     ){

     }
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

