package empire.digiprem.presentation.components.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> AppLazyVerticalGrid(
    items: List<T>,
    columns: Int,
    itemHeight: Dp,
    modifier: Modifier = Modifier,
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement. Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalArrangement: Arrangement. Horizontal = Arrangement.Start,
    content: @Composable (item: T, index: Int) -> Dp
) {
    val rows = (items.size + columns - 1) / columns
    var totalHeight by remember { mutableStateOf(itemHeight * rows) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        verticalArrangement=verticalArrangement,
        horizontalArrangement=horizontalArrangement,
        contentPadding = PaddingValues(vertical = 30.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(totalHeight)
    ) {
        itemsIndexed(items) { index, item ->
            totalHeight=(content(item, index)* rows)+100.dp
        }
    }
}
