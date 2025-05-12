package empire.digiprem.presentation.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier



@Composable
actual fun AppVerticalScrollBar(
    modifier: Modifier,
    scrollState: ScrollState
) {
    VerticalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(scrollState)
    )
}

@Composable
actual fun AppVerticalScrollBar(
    modifier: Modifier,
    lazyGridState: LazyGridState
) {
    VerticalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(lazyGridState)
    )
}

@Composable
actual fun AppVerticalScrollBar(
    modifier: Modifier,
    lazyListState: LazyListState
) {
    VerticalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(lazyListState)
    )
}