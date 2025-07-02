 package empire.digiprem.presentation.views

   import androidx.compose.foundation.layout.*
   import androidx.compose.foundation.rememberScrollState
   import androidx.compose.foundation.verticalScroll
   import androidx.compose.material.icons.Icons
   import androidx.compose.material.icons.filled.Check
   import androidx.compose.material.icons.filled.Close
   import androidx.compose.material3.*
   import androidx.compose.runtime.*
   import empire.digiprem.navigation.ViewRequestsValidation
   import androidx.lifecycle.viewmodel.compose.viewModel
   import androidx.compose.ui.Modifier
   import androidx.compose.ui.graphics.Color
   import androidx.compose.ui.unit.dp
   import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
 import empire.digiprem.presentation.viewmodels.RequestsValidationViewModel
import empire.digiprem.presentation.intents.RequestsValidationIntent
        
 @Composable
 fun RequestsValidationView(
 viewRequestsValidation:ViewRequestsValidation,
 navController: NavHostController,
 requestsvalidationViewModel:RequestsValidationViewModel = koinViewModel()
 ) {
        // val requestsvalidationViewModel:RequestsValidationViewModel = viewModel{RequestsValidationViewModel()}
         val state by requestsvalidationViewModel.state.collectAsState()
         val onSendIntent=requestsvalidationViewModel::onIntentHandler
     val demandes = remember { mutableStateListOf<DemandeCertification>() }

     ValidationDemandesPage(
         demandes,
         onRefuser = {},
         onAccepter = {}
     )
 }


 // Simuler les actions
 fun accepterDemande(demande: DemandeCertification) {
     demande.status = "ACCEPTEE"
     // Appeler API ou backend ici
 }

 fun refuserDemande(demande: DemandeCertification) {
     demande.status = "REFUSEE"
     // Appeler API ou backend ici
 }
 data class DemandeCertification(
     val id: String,
     val nom: String,
     val numeroCni: String,
     val documents: List<String>, // Chemins ou noms de fichiers
     var status: String = "EN_ATTENTE"
 )

 @Composable
 fun ValidationDemandesPage(
     demandes: List<DemandeCertification>,
     onAccepter: (DemandeCertification) -> Unit,
     onRefuser: (DemandeCertification) -> Unit,
 ) {
     Column(
         modifier = Modifier
             .fillMaxSize()
             .padding(16.dp)
             .verticalScroll(rememberScrollState())
     ) {
         Text("Demandes de certification", style = MaterialTheme.typography.titleLarge)
         Spacer(modifier = Modifier.height(16.dp))

         if (demandes.isEmpty()) {
             Text("Aucune demande en attente.")
         } else {
             demandes.forEach { demande ->
                 Card(
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(vertical = 8.dp),
                     elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                 ) {
                     Column(modifier = Modifier.padding(16.dp)) {
                         Text("👤 Nom : ${demande.nom}")
                         Text("🆔 Numéro CNI : ${demande.numeroCni}")
                         Spacer(modifier = Modifier.height(8.dp))

                         Text("📎 Documents :", style = MaterialTheme.typography.bodyMedium)
                         demande.documents.forEachIndexed { i, doc ->
                             Text("- ${doc}", style = MaterialTheme.typography.bodySmall)
                         }

                         Spacer(modifier = Modifier.height(12.dp))

                         Row(
                             horizontalArrangement = Arrangement.SpaceBetween,
                             modifier = Modifier.fillMaxWidth()
                         ) {
                             Button(
                                 onClick = { onAccepter(demande) },
                                 colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                             ) {
                                 Icon(Icons.Default.Check, contentDescription = "Accepter")
                                 Spacer(modifier = Modifier.width(8.dp))
                                 Text("Accepter")
                             }

                             Button(
                                 onClick = { onRefuser(demande) },
                                 colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                             ) {
                                 Icon(Icons.Default.Close, contentDescription = "Refuser")
                                 Spacer(modifier = Modifier.width(8.dp))
                                 Text("Refuser")
                             }
                         }
                     }
                 }
             }
         }
     }
 }
