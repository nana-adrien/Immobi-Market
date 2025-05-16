package empire.digiprem.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DropdownSelector(
    label: String,
    options: List<String>,
    selected: String?,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Column {
            Text( label.lowercase().replaceFirstChar { it.uppercaseChar() },style= MaterialTheme.typography.titleMedium.copy(Color.White))
            Row(
                modifier = Modifier.wrapContentWidth().height(45.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(10.dp))
                    .background(Color.White).clickable {
                        expanded = true
                    }.padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(selected?.let {text->text.lowercase().replaceFirstChar { it.uppercaseChar() }} ?: label.lowercase().replaceFirstChar { it.uppercaseChar() })
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = ""
                )
            }
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach {
                DropdownMenuItem(
                    text = { Text(it.lowercase().replaceFirstChar { it.uppercaseChar() }) },
                    onClick = {
                        onOptionSelected(it)
                        expanded = false
                    }
                )
            }
        }
    }
}
