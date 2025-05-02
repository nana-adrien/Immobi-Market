package empire.digiprem.ui.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun AppVerticalScrollBar(
    modifier: Modifier,
    scrollState: ScrollState?,
    lazyGridState: LazyGridState?
) {
    VerticalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(lazyGridState?:lazyGridState!!)
    )

}