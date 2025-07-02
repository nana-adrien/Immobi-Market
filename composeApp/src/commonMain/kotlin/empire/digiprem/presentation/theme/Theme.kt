package octopusfx.client.mobile.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import empire.digiprem.data.local.entities.Utilisateur
import empire.digiprem.presentation.base.color.Colors
import empire.digiprem.presentation.viewmodels.componenet.SessionManager
import org.koin.compose.viewmodel.koinViewModel


private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFE53935),       // Rouge vif adouci (moins saturé que FF0000)
    onPrimary = Color.White,

    secondary = Color(0xFFEF5350),     // Rouge clair complémentaire
    onSecondary = Color.Black,

    background = Color(0xFF121212),    // Fond sombre standard
    onBackground = Color(0xFFE0E0E0),  // Texte clair lisible

    surface = Color(0xFF1E1E1E),       // Surface légèrement différente pour contraste
    onSurface = Color(0xFFE0E0E0),

    tertiary = Color(0xFFFFCDD2),      // Rose pâle pour des accents
    onTertiary = Color.Black
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


   ResponsiveScreenComponent(sessionManager) {
       val utilisateur by LocalSessionManager.current.utilisateur.collectAsState()
       val colorScheme = if(utilisateur==null){
           if (darkTheme) DarkColorScheme else LightColorScheme
       }else{
           if (utilisateur!!.enabledlightMode) LightColorScheme else DarkColorScheme
       }
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
