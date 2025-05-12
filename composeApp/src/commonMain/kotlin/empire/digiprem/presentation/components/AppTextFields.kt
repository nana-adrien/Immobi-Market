package empire.digiprem.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    errorMessage: String = "Cependant, pour une gestion plus avancée et optimisée ",
    placeholder: String? = null,
    isError: Boolean = false,
    singleLine: Boolean = true,
    textStyle: TextStyle = TextStyle.Default.copy(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    label: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    var isFocus by remember { mutableStateOf(false) }
    Column {
        label?.let {
            Box(Modifier.wrapContentHeight()) { it() }
        }
        Row(
            modifier = Modifier.then(modifier).height(45.dp).clip(RoundedCornerShape(8.dp)).background(Color.White)
                .border(
                    width = if (isError || isFocus) 0.7.dp else 0.4.dp,
                    color = if (isError) Color.Red else if (isFocus) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.5f
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .onFocusEvent {
                    isFocus = it.isFocused
                }
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            leadingIcon?.let {
                Box(Modifier.wrapContentHeight()) { it() }
            }

            Box(Modifier.weight(1f).wrapContentHeight()) {
                if (!isFocus && value.isEmpty() || (isFocus && value.isEmpty())) {
                    placeholder?.let {
                        Box(modifier = Modifier.wrapContentHeight().padding(horizontal = 3.dp)) {
                            Text(text = it.toString(), style = textStyle.copy(color = Color.Gray), maxLines = 1)
                        }
                    }
                }
                BasicTextField(
                    modifier = Modifier.wrapContentHeight().padding(horizontal = 3.dp),
                    singleLine = singleLine,
                    value = value,
                    visualTransformation = visualTransformation,
                    onValueChange = onValueChange,
                    textStyle = textStyle
                )
            }
            trailingIcon?.let {
                Box(Modifier.wrapContentHeight()) { it() }
            }
        }
    }
    if (isError) {
        Box(Modifier.wrapContentHeight(), contentAlignment = Alignment.TopStart) {
            Text(text = errorMessage, style = MaterialTheme.typography.bodySmall.copy(color = Color.Red))
        }
    }
}

@Composable
fun AppPinCodeTextField(
    modifier: Modifier = Modifier,
    values:List<String> ,
    codeSize:Int,
    errorMessage: String = "Cependant, pour une gestion plus avancée et optimisée ",
    isError: Boolean = false,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onComplete: (List<String>) -> Unit,
) {
    val letters = remember {
        if (values.size >= codeSize) {
            values.filterIndexed { index: Int, s: String -> index<codeSize }.map { mutableStateOf(it) }
        }
        else{
           val tempLists= values.filterIndexed { index: Int, s: String -> index<codeSize }.map { mutableStateOf(it) }.toMutableList()
            val index=values.size
          for ( i in index..<codeSize) { tempLists+=mutableStateOf("") }
            tempLists
        }
    }
    val focusRequesters = remember { List(codeSize) { FocusRequester() } }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        letters.forEachIndexed { index, letterState ->
            BasePinCodeTextField(
                value = letterState.value,
                placeholder = "${index + 1}",
                textStyle = textStyle,
                visualTransformation = visualTransformation,
                modifier = Modifier
                    .focusRequester(focusRequesters[index])
                    .onPreviewKeyEvent { keyEvent ->
                        if (keyEvent.key == Key.Backspace &&
                            keyEvent.type == KeyEventType.KeyDown &&
                            letterState.value.isEmpty() &&
                            index > 0
                        ) {
                            focusRequesters[index - 1].requestFocus()
                            true
                        } else {
                            false
                        }
                    },

                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (index < codeSize - 1) ImeAction.Next else ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        if (index < codeSize - 1) focusRequesters[index + 1].requestFocus()
                    },
                    onDone = {
                        onComplete(letters.map { it.value })
                    }
                ),
                onValueChange = { input ->
                    val filtered = input.take(1).filter {it.isDigit() }

                    if (filtered != letterState.value) {
                        letterState.value = filtered
                        if (filtered.isNotEmpty() && index < codeSize - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }
                        if (index == codeSize - 1 && filtered.isNotEmpty()) {
                            onComplete(letters.map { it.value })
                        }
                    }
                },
            )
        }
    }
    if (isError) {
        Box(Modifier.wrapContentHeight(), contentAlignment = Alignment.TopStart) {
            Text(text = errorMessage, style = MaterialTheme.typography.bodySmall.copy(color = Color.Red))
        }
    }
}

@Composable
fun RowScope.BasePinCodeTextField(
    isError: Boolean = false,
    value: String,
    modifier: Modifier = Modifier,
    placeholder: String?,
    textStyle: TextStyle,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation,
    onValueChange: (String) -> Unit
) {
    var isFocus by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.then(modifier).height(45.dp).weight(0.2f).clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(
                width = if (isError || isFocus) 0.7.dp else 0.4.dp,
                color = if (isError) Color.Red else if (isFocus) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                    alpha = 0.5f
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusEvent {
                isFocus = it.isFocused
            }
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(Modifier.weight(1f).wrapContentHeight(),contentAlignment = Alignment.TopStart) {
            if (!isFocus && value.isEmpty() || (isFocus && value.isEmpty())) {
                placeholder?.let {
                    Box(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(horizontal = 3.dp),contentAlignment = Alignment.Center) {
                        Text(
                            text = it.toString(),
                            style = textStyle.copy(color = Color.Gray, fontWeight = FontWeight.Normal,textAlign = TextAlign.Center),
                            maxLines = 1
                        )
                    }
                }
            }
            BasicTextField(
                modifier = Modifier.wrapContentHeight().padding(horizontal = 3.dp),
                singleLine = true,
                value = value,
                visualTransformation = visualTransformation,
                onValueChange = onValueChange,
                keyboardActions = keyboardActions,
                keyboardOptions = keyboardOptions,
                textStyle = textStyle
            )
        }
    }

}