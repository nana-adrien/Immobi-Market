package empire.digiprem.presentation.components.forms

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import empire.digiprem.model.AppFile
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import kotlin.math.roundToInt

interface Execute {
    fun execute(input: String="image/*")
}


@Composable
expect fun PickStorageFile(input: String = "image/*", onResult: (AppFile?) -> Unit): Execute
@Composable
expect fun PickStorageFiles(onResult: (List<AppFile>) -> Unit): Execute
@Composable
expect fun TakePicture(onResult: (AppFile?) -> Unit): Execute

/*

@Composable
fun AddFilesEx(
    files: List<AppFile>,
    maxFiles: Int = 2,
    onInsertFiles: (List<AppFile>) -> Unit,
    requestPermission: MutableState<CameraPermissions>,
    onDeleteFile: (AppFile) -> Unit
) {
    FilesPicker(
        enabled = (files.size >= maxFiles).not(),
        title = if (files.isEmpty()) "Ajoueter des pieces jointes" else "Fichier ajouter(${files.size}/$maxFiles)",
        requestPermission = requestPermission,
        onPickFiles = {
            val appFiles = files.toMutableSet()
            it.forEach { file ->
                if (!appFiles.contains(file)) {
                    appFiles.add(file)
                } else {
                    //("fichier${file.name}déjà ajouté.")
                }
            }
            onInsertFiles(appFiles.toList())
        }
    )
    if (files.size > maxFiles) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "vous avez dépassé le nombre maximal de fichier à importer",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
    ShowFiles(
        uriList = files,
        onDeleteFileUri = onDeleteFile
    )
}

@Composable
fun FilesPicker(
    isVisible: Boolean = true,
    title: String = "",
    titleResource: StringResource? = null,
    onPickFiles: (List<AppFile>) -> Unit,
    requestPermission: MutableState<CameraPermissions>,
    enabled: Boolean = true
) {

    val permissionsService = koinInject<PermissionsService>()
    var cameraPermissions by remember {
        mutableStateOf(
            permissionsService.checkPermission(
                PermissionEnum.CAMERA
            )
        )
    }
    var expanded by remember { mutableStateOf(false) }
    val showPlasholder = (title == "" || title == "0")
    var title_Content by remember { mutableStateOf("") }
    title_Content = title

    val takePicture = TakePicture { file -> file?.let { onPickFiles(listOf(file)) } }
    val imagePicker = PickStorageFile { file -> file?.let { onPickFiles(listOf(file)) } }
    val filesPicker = PickStorageFiles { files -> onPickFiles(files) }

    AnimatedVisibility(visible = isVisible) {
        CardContent {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ClickDelay { actived, onNavigateClick ->
                    val onClick = {
                        if (enabled) {
                            onNavigateClick()
                            expanded = !expanded
                        } else {
                            ToastNotification("Nombre maximal de fichiers importés")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = actived, onClick = onClick)
                            .padding(start = 15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.weight(3f),
                            text = if (titleResource != null) stringResource(titleResource) else if (!showPlasholder) title_Content else "                    //TODO \"Translation :Value not set\"\n",
                            color = if (showPlasholder) Color.Gray else MaterialTheme.typography.headlineLarge.color,
                            style = if (showPlasholder) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Left,
                        )
                        IconButton(
                            enabled = actived,
                            modifier = Modifier
                                .weight(0.5f)
                                .background(MaterialTheme.colorScheme.background),
                            onClick = onClick
                        )
                        {
                            AppIcon(
                                painter = painterResource(Res.drawable.attach_file),
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }
                AnimatedVisibility(
                    visible = expanded,
                    enter = expandVertically(
                        animationSpec = tween(300),
                        clip = false,
                        expandFrom = Alignment.Bottom
                    ),
                    exit = shrinkOut(animationSpec = tween(300))
                ) {
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        },
                        shadowElevation = 0.dp,
                        tonalElevation = 0.dp,
                        border = BorderStroke(0.dp, Color.Transparent),
                    ) {
                        DropdownMenuItem(
                            title = "Camera",
                            icon = Res.drawable.baseline_photo_camera_24,
                            modifier = Modifier.padding(vertical = 2.dp),
                            onSelectItem = {
                                when (cameraPermissions) {
                                    PermissionStateEnum.GRANTED -> {
                                        //takeCapture.launch(photoUri)
                                        takePicture.execute()
                                    }
                                    PermissionStateEnum.NOT_DETERMINED, PermissionStateEnum.DENIED -> {
                                        requestPermission.value = requestPermission.value.copy(
                                            enabled = true,
                                            permissionEnum = PermissionEnum.CAMERA,
                                            listOfUIPermissionState = listOf(
                                                UIPermissionState(
                                                    "Camera",
                                                    PermissionEnum.CAMERA,
                                                    Res.drawable.baseline_photo_camera_24,
                                                    isGranted = !cameraPermissions.notGranted()
                                                ),
                                            ),
                                            onPermissionGrand = {
                                                cameraPermissions = it
                                            },
                                            onPermissionDenied = {
                                                requestPermission.value =
                                                    requestPermission.value.copy(enabled = false)
                                            }
                                        )
                                    }
                                }
                                expanded = false
                            })

                        DropdownMenuItem(
                            title = "Gallery",
                            icon = Res.drawable.baseline_image_24,
                            modifier = Modifier.padding(vertical = 2.dp),
                            onSelectItem = {
                                imagePicker.execute()
                                expanded = false
                            })
                        DropdownMenuItem(
                            title = "Documents",
                            icon = Res.drawable.document,
                            modifier = Modifier.padding(vertical = 2.dp),
                            onSelectItem = {
                                filesPicker.execute()
                                expanded = false
                            })
                    }
                }
            }
        }
    }
}


@Composable
fun ShowFiles(uriList: List<AppFile>, onDeleteFileUri: (AppFile) -> Unit) {
    val orientation = LocalAppConfiguration.current.orientation
    val numCells = if (orientation == AppOrientation.ORIENTATION_PORTRAIT) 3 else 5
    val heightGridCells =
        (if (uriList.size == 1) uriList.size else ((uriList.size.toFloat() / numCells) + 0.2).roundToInt()) * 125
    if (uriList.isNotEmpty()) {
        CardContent {
            LazyVerticalGrid(
                columns = GridCells.Fixed(numCells),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp)
                    .height(heightGridCells.dp)
            ) {
                items(items = uriList) { appFile ->
                    UIFile(
                        appFile,
                        onDeleteFileUri = { onDeleteFileUri.invoke(appFile) })
                }
            }
        }
    }
}

@Composable
internal fun UIFile(appFile: AppFile, onDeleteFileUri: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .size(80.dp)
        ) {
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .fillMaxSize()
            )
            {
                when (appFile.mimeType) {
                    FileUriTypeEnum.IMAGE -> {
                        if (appFile.byteArray != null) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                bitmap = appFile.byteArray.decodeToImageBitmap(),
                                contentScale = ContentScale.Crop,
                                contentDescription = ""
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Color.Black
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    appFile.mimeType.name,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )
                            }
                        }
                    }

                    else -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    if (appFile.mimeType == FileUriTypeEnum.PDF) Color.Red.copy(
                                        alpha = 0.7f
                                    ) else Color.Unspecified.copy(
                                        alpha = 0.7f
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                appFile.mimeType.name,
                                style = TextStyle(fontWeight = FontWeight.Bold, color = Color.White)
                            )
                        }
                    }
                }
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors()
                        .copy(containerColor = Color.Black.copy(alpha = 0.2f)),
                    onClick = onDeleteFileUri
                ) {
                    AppIcon(
                        painter = painterResource(Res.drawable.baseline_close_24),
                        color = Color.White,
                        size = 25.dp
                    )
                }
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = appFile.name,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            style = MaterialTheme.typography.bodySmall
        )
    }


}

*/
