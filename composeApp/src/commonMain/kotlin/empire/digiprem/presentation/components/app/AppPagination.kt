package empire.digiprem.presentation.components.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AppPagination(
    modifier: Modifier = Modifier,
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit,
    maxVisiblePages: Int = 7
) {
    val pages = remember(currentPage, totalPages, maxVisiblePages) {
        val start = maxOf(1, currentPage - maxVisiblePages / 2)
        val end = minOf(totalPages, start + maxVisiblePages - 1)
        (start..end).toList()
    }

    Row(
        modifier = modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (currentPage > 1) {
            Text(
                text = "<",
                modifier = Modifier
                    .clickable { onPageSelected(currentPage - 1) }
                    .padding(horizontal = 8.dp),
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        }

        pages.forEach { page ->
            Text(
                text = page.toString(),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clickable { onPageSelected(page) }
                    .background(
                        if (page == currentPage) Color.Blue else Color.Transparent,
                        shape = CircleShape
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                color = if (page == currentPage) Color.White else Color.Black
            )
        }

        if (currentPage < totalPages) {
            Text(
                text = ">",
                modifier = Modifier
                    .clickable { onPageSelected(currentPage + 1) }
                    .padding(horizontal = 8.dp),
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


fun  <T> List<T>.paginateList( page: Int, pageSize: Int): List<T> {
    val fromIndex = (page - 1) * pageSize
    val toIndex = (fromIndex + pageSize).coerceAtMost(size)
    return if (fromIndex in 0 until size) {
        subList(fromIndex, toIndex)
    } else {
        emptyList()
    }
}
