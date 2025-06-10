package octopusfx.client.mobile.core.ui.screens.components.forms

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import empire.digiprem.core.utils.Utils.currentDateFormatter
import empire.digiprem.core.utils.AppFileUtils.createAppFile
import java.io.File


@Composable
actual fun PickAndCropImageContract(
    input: String,
    onResult: (AppFile?) -> Unit
): ExecutePickAndCropImage {
    /*val context = LocalContext.current
    val pickImages = rememberLauncherForActivityResult(contract = CropImageContract()) { result ->
        if (result.isSuccessful) {
            result.uriContent?.let {
                onResult(createAppFile(context, it))
            }
        } else {
            println("ImageCropping error: ${result.error}")
        }
    }
    return object : ExecutePickAndCropImage {
        override fun execute(
            imageSourceIncludeGallery: Boolean,
            imageSourceIncludeCamera: Boolean
        ) {
            val cropOptions = CropImageContractOptions(
                null,
                CropImageOptions(
                    imageSourceIncludeGallery = imageSourceIncludeGallery,
                    imageSourceIncludeCamera = imageSourceIncludeCamera
                )
            )
            pickImages.launch(cropOptions)
        }
    }*/
    return object : ExecutePickAndCropImage {
        override fun execute(imageSourceIncludeGallery: Boolean, imageSourceIncludeCamera: Boolean) {
            TODO("Not yet implemented")
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
        override fun execute() {
            takeCapture.launch(photoUri)
        }
    }
}


@Composable
actual fun PickStorageFile(onResult: (AppFile?) -> Unit): ExecuteInput<String> {
    val context = LocalContext.current
    val takeImages =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { result ->
            if (result != null) {
                onResult(createAppFile(context, result))
            }
        }
    return object : ExecuteInput<String>  {
        override fun execute(input: String) {
            takeImages.launch(input)
        }
    }

}

@Composable
actual fun PickStorageFiles(onResult: (List<AppFile>) -> Unit): ExecuteInput<List<String>> {
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
    return object : ExecuteInput<List<String>> {
        override fun execute(input: List<String>) {
            pickFile.launch(input.toTypedArray())
        }
    }
}