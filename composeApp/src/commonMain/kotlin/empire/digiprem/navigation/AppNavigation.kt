package empire.digiprem.navigation

/*@Auto import file*/

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlin.reflect.KClass

/*@Auto Generate*/
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: KClass<*>,
    onNavigate: (String?) -> Unit
) {
    NavHost(
        navController,
        modifier = modifier,
        startDestination = startDestination,
    ) {
        /*@Auto Generate Composable*/
    }
}