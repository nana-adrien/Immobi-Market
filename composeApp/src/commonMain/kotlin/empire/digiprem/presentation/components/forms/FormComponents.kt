package empire.digiprem.presentation.components.forms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import composeApp.src.commonMain.ComposeResources.drawable.IM_Plan_de_travail_1
import composeApp.src.commonMain.ComposeResources.drawable.Res
import composeApp.src.commonMain.ComposeResources.drawable.background_immeuble
import empire.digiprem.config.isCompactApplication
import empire.digiprem.model.AppFile
import empire.digiprem.presentation.components.AppIconActionButton
import empire.digiprem.presentation.components.AppTextField
import empire.digiprem.presentation.components.forms.PickStorageFiles
import empire.digiprem.presentation.views.AppCardWrapperEx
import empire.digiprem.presentation.views.SelectContentTextField
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun FormContentWrapper(title: String = "", cardModifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        if (title.isNotEmpty()) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(start = 10.dp),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.titleLarge,
                )
                Box(Modifier.height(3.dp).width(60.dp) .clip(RoundedCornerShape(topEnd = 7.dp, bottomEnd = 7.dp)).background(MaterialTheme.colorScheme.secondary.copy(0.5f)))
            }
        }
        AppCardWrapperEx(
            modifier = cardModifier.clip(RoundedCornerShape(10.dp)).background(Color.LightGray.copy(alpha = 0.2f))
                .padding(20.dp),
        ) {
            content()
        }
    }
}


data class Option(val id: String, val name: String)

@Composable
fun SelectMultipOptions(
    //label: String,
    enabledModification: Boolean = true,
    options: List<Option>,
    selectedOptions: List<String>,
    onSelectOption: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        options.forEach { option ->
            val isSelected = selectedOptions.contains(option.id)
            Box(
                modifier = Modifier.wrapContentSize().clip(RoundedCornerShape(7.dp))
                    .background(if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else MaterialTheme.colorScheme.background)
                    .clickable { onSelectOption(option.id) }
                    .padding(horizontal = 8.dp, vertical = 6.dp), contentAlignment = Alignment.Center
            ) {
                Text(
                    option.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isSelected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.7f
                    )
                )
            }
        }
    }


    /*FormTextFieldWrapper(
        enabledModification = enabledModification,
        enabledClick = false,
        label = label
    ) {




    }*/
}

@Composable
fun FormSelectImages(
    selectedImage: Any?,
    images: List<Any>,
    onSelectedImageChange: (Any?) -> Unit,
    onSelectImages: (List<Any>) -> Unit,
    onRemoveImage: (Any?) -> Unit
) {
    Column(
        modifier = Modifier.wrapContentSize(), verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val pickImageFiles = PickStorageFiles { onSelectImages(it) }
        val contentImage: @Composable (Any) -> Unit = @Composable { image ->
            when (image) {
                is String -> {
                    AsyncImage(
                        model = image,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }

                is DrawableResource -> {
                    Image(
                        painter = painterResource(image as DrawableResource),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                is AppFile -> {
                    (image as AppFile).byteArray?.let {
                        Image(
                            bitmap = it.decodeToImageBitmap(),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                else -> {
                    Text("Pas d'affichage pour ce format d'image ")
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth().heightIn(max =  if (isCompactApplication()) 300.dp else 500.dp)
                .shadow(elevation = 2.dp).background(MaterialTheme.colorScheme.background)
                .clickable(enabled = images.isEmpty()) { pickImageFiles.execute() },
            contentAlignment = Alignment.Center
        ) {
            if (selectedImage != null || images.isNotEmpty()) {
                contentImage(selectedImage ?: images.first())
                Box(
                    modifier = Modifier.align(Alignment.TopEnd).size(50.dp)
                        .shadow(elevation = 0.2.dp, shape = RoundedCornerShape(7.dp)).background(Color.White)
                        .clickable { onRemoveImage(selectedImage);onSelectedImageChange(null) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete, ""
                    )
                }
            } else {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.size(80.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddAPhoto, ""
                        )
                    }
                    if (images.isEmpty()) {
                        Text("Pick Images")
                    } else {
                        Text("select Image")
                    }
                }

            }


        }
        if (images.isNotEmpty()) {
            Row(
                modifier = Modifier.height(60.dp).fillMaxWidth().shadow(elevation = 0.5.dp)
                    .background(MaterialTheme.colorScheme.background),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Box(
                    modifier = Modifier.size(80.dp).shadow(elevation = 0.2.dp).clickable { pickImageFiles.execute() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AddAPhoto, ""
                    )
                }
                LazyRow(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(items = images) {
                        Box(modifier = Modifier.fillMaxHeight().width(80.dp).shadow(elevation = 0.2.dp).clickable {
                            onSelectedImageChange(it)
                        }) {
                            contentImage(it)
                            Box(
                                modifier = Modifier.align(Alignment.TopEnd).size(20.dp).shadow(elevation = 0.2.dp)
                                    .clickable {
                                        onRemoveImage(it)
                                        if (it == selectedImage) {
                                            onSelectedImageChange(null)
                                        }
                                    }, contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete, ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FormTextFieldWrapper(
    label: String = "",
    enabledModification: Boolean = true,
    enabledClick: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit) = {},
    trealingContent: @Composable () -> Unit,
) {
    AppCardWrapperEx(
        modifier = Modifier
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(7.dp))
            .background(if (enabledModification) Color.White else Color.LightGray.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier.then(modifier).fillMaxWidth().height(45.dp)
                .clickable(enabled = enabledModification && enabledClick, onClick = onClick)
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "$label",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.W600,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = if (enabledModification) 1f else 0.5f)
            )

            trealingContent.invoke()
        }
    }
}


@Composable
fun IncrementeDecrementValue(
    label: String,
    value: Int = 0,
    minValue: Int = 0,
    maxValue: Int = 100,
    enabledModification: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (Int) -> Unit,
) {
    val button: @Composable (Boolean, ImageVector, () -> Unit) -> Unit =
        @Composable { enabled, imageVector, onClickText ->
            AppIconActionButton(
                enabled = enabled,
                modifier = Modifier.size(20.dp).clip(CircleShape).clickable(onClick = onClickText),
                onClick = onClickText
            ) {
                Icon(imageVector = imageVector, contentDescription = null)
            }
        }
    FormTextFieldWrapper(
        enabledModification = enabledModification,
        enabledClick = false,
        label = label
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (enabledModification) {
                button(value > minValue, Icons.Default.Remove) {
                    val index = if (value > minValue) value - 1 else value
                    onValueChange(index)
                }
            }
            Text(
                "$value",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = if (enabledModification) 1f else 0.5f)
            )
            trailingIcon?.invoke()
            if (enabledModification) {
                button(value < maxValue, Icons.Default.Add) {
                    val index = if (value < maxValue) value + 1 else value
                    onValueChange(index)
                }
            }
        }
    }
}

@Composable
fun SwitchTextField(
    label: String,
    enabledModification: Boolean = true,
    enabled: Boolean = true,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    FormTextFieldWrapper(

        enabledModification = enabledModification,
        label = label,
        onClick = { onValueChange(!value) }
    ) {
        Switch(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 15.dp),
            checked = value,
            onCheckedChange = onValueChange,
            enabled = enabled && enabledModification
        )
    }
}

@Composable
fun EdithTextField(
    label: String,
    enabledModification: Boolean = true,
    enabled: Boolean = true,
    value: String,
    trailingIcon: @Composable() (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    FormTextFieldWrapper(

        enabledModification = enabledModification,
        label = label,
        enabledClick = false,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (enabledModification) {
                Box(
                    modifier = Modifier.wrapContentHeight().widthIn(min = 150.dp, max = 250.dp)
                        .padding(vertical = 4.dp, horizontal = 15.dp)
                ) {
                    AppTextField(
                        enabled = enabled,
                        value = value,
                        onValueChange = onValueChange,
                        trailingIcon = trailingIcon
                    )
                }
            } else {
                Box(
                    modifier = Modifier.wrapContentSize().clip(RoundedCornerShape(7.dp))
                        .background(MaterialTheme.colorScheme.surface).padding(horizontal = 3.dp, vertical = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        value,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                trailingIcon?.invoke()
            }
        }

    }
}

@Composable
fun CheckTextField(
    label: String,
    enabled: Boolean = true,
    enabledModification: Boolean = true,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    FormTextFieldWrapper(

        enabledModification = enabledModification,
        label = label,
        onClick = { onValueChange(!value) }
    ) {
        Checkbox(
            modifier = Modifier.padding(horizontal = 15.dp),
            checked = value,
            onCheckedChange = onValueChange,
            enabled = enabled && enabledModification
        )
    }
}

@Composable
fun RadioTextField(
    label: String,
    enabled: Boolean = true,
    enabledModification: Boolean = true,
    selectedOption: String,
    onSelectOption: (String) -> Unit,
    option: List<String>
) {
    FormTextFieldWrapper(
        enabledModification = enabledModification,
        label = label, enabledClick = false,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            option.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.padding(horizontal = 5.dp)
                        .clickable(enabled && enabledModification) { onSelectOption(it) }
                ) {
                    RadioButton(
                        selected = it == selectedOption,
                        onClick = { onSelectOption(it) },
                        enabled = enabled && enabledModification
                    )
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }

            }
        }
    }
}
