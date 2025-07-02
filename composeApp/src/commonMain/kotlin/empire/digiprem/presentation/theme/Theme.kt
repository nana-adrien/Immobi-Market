package octopusfx.client.mobile.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import empire.digiprem.app.model.components.Utilisateur
import empire.digiprem.presentation.base.color.Colors
import empire.digiprem.presentation.viewmodels.componenet.SessionManager
import org.koin.compose.viewmodel.koinViewModel

private val DarkColorScheme = darkColorScheme(
    primary = PurpleGrey80,
    secondary = Purple80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = Colors.primary,
    secondary =Colors.secondary,
    tertiary = Pink40,
    //Other default colors to override
    background = Colors.background,
    surface = Colors.surface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),

    )


@Composable
fun AppTheme(
    sessionManager: SessionManager = koinViewModel(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        /* dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
             val context = LocalContext.current
             if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
         }*/
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
   ResponsiveScreenComponent(sessionManager) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}
@Composable
fun ResponsiveScreenComponent(sessionManager: SessionManager, content:@Composable () -> Unit) {
    val utilisateurState = sessionManager.utilisateur.collectAsState()
    CompositionLocalProvider(LocalUtilisateur provides utilisateurState, LocalSessionManager provides sessionManager) {
        content()
    }
}
val LocalUtilisateur = compositionLocalOf<State<Utilisateur?>> {  error("Utilisateur non initialisé") }
val LocalSessionManager = compositionLocalOf<SessionManager> {  error("SessionManager non initialisé") }
