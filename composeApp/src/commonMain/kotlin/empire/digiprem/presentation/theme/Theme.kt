package octopusfx.client.mobile.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PurpleGrey80,
    secondary = Purple80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink40,
    //Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),

    )


@Composable
fun AppToolsTheme(
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
    /*val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }*/
  //  val appSettings by remember { mutableStateOf() }
/*    val appTools by remember { mutableStateOf(AppTools()) }
    //val appConfiguration by remember { mutableStateOf(getAppConfiguration()) }
    ResponsiveScreenComponent(
 //       appSettings = appSettings,
        appConfiguration = getAppConfiguration(),
        appTools = appTools
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = FokoTrialTypography(),
            content = content
        )
    }*/
}

