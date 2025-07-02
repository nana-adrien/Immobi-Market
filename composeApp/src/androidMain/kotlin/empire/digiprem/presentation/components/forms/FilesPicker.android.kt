package empire.digiprem.presentation.components.forms

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import empire.digiprem.core.utils.Utils.currentDateFormatter
import empire.digiprem.enums.FileUriTypeEnum
import empire.digiprem.model.AppFile
import java.io.File


@Composable
actual fun PickStorageFiles(onResult: (List<AppFile>) -> Unit): Execute {
    val context = LocalContext.current
    val pickFile =
        rememberLauncherForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) { uris ->
            if (uris.isNotEmpty()) {
                val appFiles = mutableListOf<AppFile>()
                uris.forEach { uri ->
                    createAppFile(context, uri)?.let {
                        appFiles.add(it)
                    }
                }
                onResult(appFiles)
            }
        }
    return object : Execute {
        override fun execute(input: String) {
            pickFile.launch(arrayOf("application/*"))
        }
    }
}

@Composable
actual fun PickStorageFile(input: String, onResult: (AppFile?) -> Unit): Execute {
    val context = LocalContext.current
    val takeImages =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { result ->
            if (result != null) {
                onResult(createAppFile(context, result))
            }
        }
    return object : Execute {
        override fun execute(input: String,) {
            takeImages.launch(input)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
actual fun TakePicture(onResult: (AppFile?) -> Unit): Execute {
    val context = LocalContext.current
    val photoUri =
        FileProvider.getUriForFile(
            context,
            context.packageName.toString(),
            File(context.cacheDir, "Image_${currentDateFormatter()}.jpg")
        )
    val takeCapture =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { result ->
            if (result) {
                onResult(createAppFile(context, photoUri))
            }
        }
    return object : Execute {
        override fun execute(input: String) {
            takeCapture.launch(photoUri)
        }
    }
}


fun createAppFile(
    context: android.content.Context,
    uri: android.net.Uri
): AppFile? {
    return try {
        val file = File(uri.path ?: return null)
        AppFile(
            byteArray = context.contentResolver.openInputStream(uri)?.readBytes(),
            name = file.name,
            mimeType = FileUriTypeEnum.fromExtension("${context.contentResolver.getType(uri)}"),
            mimeTypeDescriptor = file.extension,
            path = file.path
        )
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

