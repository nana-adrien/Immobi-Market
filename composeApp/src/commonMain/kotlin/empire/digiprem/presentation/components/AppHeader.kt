package empire.digiprem.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import empire.digiprem.config.isCompactMobilePlatform


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader(
    containerColor : Color = MaterialTheme.colorScheme.surfaceVariant,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable ( RowScope.() -> Unit )
) {
    val isCompactSize = isCompactMobilePlatform()

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = containerColor),
        navigationIcon = navigationIcon,
        title = { Text("IMMOBI Market" , style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = if(isCompactSize) 19.sp else 25.sp
        )) },
        actions = actions
    )
}