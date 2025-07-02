package empire.digiprem

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import empire.digiprem.navigation.AppWebDesktopNavigationConfig
import empire.digiprem.navigation.ApplicationNavigationController
import empire.digiprem.ui.PageAddRealStatePreview
import octopusfx.client.mobile.core.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(   navController: NavHostController? = null, modifier: Modifier = Modifier,scrollState: ScrollState?=null) {
    val appNavController = navController ?: rememberNavController()
    val appScrollState =scrollState?:rememberScrollState(0)
    AppTheme{
        ApplicationNavigationController(
            modifier=Modifier.fillMaxSize(),
            navController = appNavController,
            appScrollState=appScrollState
        )
       // PageAddRealStatePreview()
    }
}


