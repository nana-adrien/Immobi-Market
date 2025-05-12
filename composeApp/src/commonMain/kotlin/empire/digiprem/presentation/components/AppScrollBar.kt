package empire.digiprem.presentation.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun AppVerticalScrollBar(modifier: Modifier = Modifier, scrollState: ScrollState)
@Composable
expect fun AppVerticalScrollBar(modifier: Modifier = Modifier, lazyGridState: LazyGridState)
@Composable
expect fun AppVerticalScrollBar(modifier: Modifier = Modifier, lazyListState: LazyListState)