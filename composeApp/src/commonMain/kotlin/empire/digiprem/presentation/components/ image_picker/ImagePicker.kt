package empire.digiprem.presentation.components.` image_picker`

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
data class ImageData(
    val byteArray: ByteArray?,
    val name: String?,
    val mimeType: String?,
    val path: String? = null // si tu l’as (ce qui n'est souvent pas le cas en Web)
)
expect  fun pickImage(onResult:(ImageData?)->Unit)

@Composable
fun ImageSelector(onImagePicked: (ImageData?) -> Unit) {
    Button(onClick = { pickImage(onImagePicked) }) {
        Text("Choisir une image")
    }
}


