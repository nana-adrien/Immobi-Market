package empire.digiprem.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import empire.digiprem.config.isCompactMobilePlatform


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader(
    containerColor : Color = Color.Transparent,
    modifier:Modifier=Modifier,
    navigationIcon: @Composable () -> Unit = {},
    title: @Composable () -> Unit={},
    actions: @Composable ( RowScope.() -> Unit )
) {
    TopAppBar(
        modifier=modifier,
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = containerColor),
        navigationIcon = navigationIcon,
        title = title,
        actions = actions
    )
}