package empire.digiprem.presentation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.datetime.*


@Composable
fun AppScrollableDialog(
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    scrollState: ScrollState = rememberScrollState(),
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(scrollState) // << scroll actif même autour
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                val event = awaitPointerEvent()
                                // Intercepter uniquement les clics
                                if (event.changes.any { it.changedToUp() || it.changedToDown() }) {
                                    onDismissRequest()
                                    event.changes.forEach { it.consume() }
                                }
                            }
                        }
                    }
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.then(modifier).fillMaxWidth(0.7f).fillMaxHeight()
                        .padding(top = 100.dp)// Limite la hauteur pour permettre le scroll
                        .background(Color.White, shape = RoundedCornerShape(10.dp))
                        .pointerInput(Unit) {
                            awaitPointerEventScope {
                                while (true) {
                                    val event = awaitPointerEvent()
                                    if (event.changes.any { it.changedToUp() || it.changedToDown() }) {
                                        event.changes.forEach { it.consume() }
                                    }
                                }
                            }
                        }
                ) {
                    content()
                }
            }
            AppVerticalScrollBar(
                modifier = Modifier.align(Alignment.CenterEnd),
                scrollState = scrollState
            )
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDataPicker() {

    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { (mutableStateOf("")) }
    var showError by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        DataPickerCard(
            title = "Date Picker",
            description = "Select a date for your current user",
            date = selectedDate,
            buttonText ="Select date",
            onClick = { showDatePicker = true }
        )
        if (showDatePicker) {
            val datePickerState= rememberDatePickerState(
                selectableDates = object : SelectableDates{
                    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                        val date=Instant.fromEpochMilliseconds(utcTimeMillis).toLocalDateTime(
                            TimeZone.currentSystemDefault()
                        ).date
                        return validateDate(date,RestrictionType.NoPastDates)
                    }
                }
            )

            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        showDatePicker = false
                    val selectedMillis=datePickerState.selectedDateMillis
                        if (selectedMillis!=null){
                            val date=Instant.fromEpochMilliseconds(selectedMillis).toLocalDateTime(TimeZone.currentSystemDefault()).date
                            if (!validateDate(date,RestrictionType.NoPastDates)){
                                showError=true
                            } else {
                                selectedDate=date.toString()
                                showDatePicker = false
                                showError=false
                            }
                        }
                    }) {
                        Text("Ok")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {showDatePicker=false}){
                        Text("Cancel",color = Color.White)
                    }
                },
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = Color.White,
                )
            ){
                Column {
                    DatePicker(
                        state = datePickerState,
                        headline = {
                            Text(
                                text="Future Only",
                                style = MaterialTheme.typography.titleLarge,
                                color = Color(0xfff3f51885),
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    )
                    if (showError){
                        Text(
                            text = "Selected Date is not allowed. Please choose a valid date",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

            }

        }

    }

}

@Composable
fun DataPickerCard(title: String, description: String, date: String, buttonText: String, onClick: () -> Unit) {

    Card(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).border(
            1.dp,Color.Gray.copy(alpha = 0.2f),RoundedCornerShape(16.dp),),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {

        Column(
            Modifier.padding(16.dp)
        ) {

            Text(
                text=title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier=Modifier.height(8.dp))

            Text(
                text=description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
            Spacer(modifier=Modifier.height(12.dp))

            Button(
                onClick = onClick,
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(12.dp)
            ){
                Text(buttonText, fontSize = 16.sp)
            }
            if (date.isNotEmpty()){
                Spacer(Modifier.height(6.dp))

                Text(
                    text="selected $date",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }


    }

}


private fun validateDate(date:LocalDate,restriction:RestrictionType):Boolean{
    val today= Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    return when(restriction){
        RestrictionType.NoPastDates-> date>= today
        else -> true
    }
}

enum class RestrictionType {
    None,
    NoPastDates,
}

