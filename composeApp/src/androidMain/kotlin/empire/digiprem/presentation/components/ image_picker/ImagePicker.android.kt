package empire.digiprem.presentation.components.` image_picker`

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
actual fun pickImage(onResult: (ImageData?) -> Unit) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) {
                uri: Uri? ->
            uri?.let {
                context.contentResolver.openInputStream(it)?.use { stream ->
                    val bytes = stream.readBytes() // conversion en ByteArray
                    onResult(bytes)
                }
            } ?: onResult(null)
        }
// au clic :
    launcher.launch(arrayOf("image/*"))
}