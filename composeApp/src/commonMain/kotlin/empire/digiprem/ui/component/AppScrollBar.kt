package empire.digiprem.ui.component

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun AppVerticalScrollBar(modifier: Modifier = Modifier, scrollState: ScrollState)