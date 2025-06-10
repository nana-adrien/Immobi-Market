 package empire.digiprem.presentation.views

   import androidx.compose.foundation.layout.*
   import androidx.compose.foundation.pager.HorizontalPager
   import androidx.compose.foundation.pager.rememberPagerState
   import androidx.compose.foundation.verticalScroll
   import androidx.compose.material3.*
   import androidx.compose.runtime.*
   import androidx.compose.ui.Alignment
   import empire.digiprem.navigation.ViewPropertyALLProperty
   import androidx.lifecycle.viewmodel.compose.viewModel
   import androidx.compose.ui.Modifier
   import androidx.compose.ui.text.font.FontWeight
   import androidx.compose.ui.unit.dp
   import androidx.compose.ui.unit.sp
   import org.koin.compose.viewmodel.koinViewModel
  import androidx.navigation.NavHostController
   import empire.digiprem.navigation.EnumView
   import empire.digiprem.presentation.components.AppVerticalScrollBar
   import empire.digiprem.presentation.components.wrapper.PageWrapperState
   import empire.digiprem.presentation.components.wrapper.WebDesktopPageWrapper
   import empire.digiprem.presentation.viewmodels.PropertyALLPropertyViewModel
import empire.digiprem.presentation.intents.PropertyALLPropertyIntent
   import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.BienImmobilierViewModel
   import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.Etape1_InfoGenerales
   import empire.digiprem.ui.Screen.dashboard_screen.screens.mes_annonces.Etape2_Localisation
   import kotlinx.coroutines.launch

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