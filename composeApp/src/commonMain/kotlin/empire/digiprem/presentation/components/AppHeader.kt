package empire.digiprem.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader(
    actions: @Composable ( RowScope.() -> Unit )
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = MaterialTheme.colorScheme.primaryContainer),
        title = { Text("IMMOBI Marcket") },
        actions = actions
    )
}