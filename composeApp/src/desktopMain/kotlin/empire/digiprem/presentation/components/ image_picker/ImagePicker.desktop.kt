package empire.digiprem.presentation.components.` image_picker`

import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

actual fun pickImage(onResult: (ImageData?) -> Unit) {
    val chooser = JFileChooser().apply {
        fileFilter = FileNameExtensionFilter("Images", "jpg", "png", "jpeg")
    }
    /*if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        val file = chooser.selectedFile
        val bytes = file.readBytes()
        onResult{
            ImageData(
                name = file.name,
                mimeType =file.extension ,
                path =file.path ,
                byteArray = bytes
            )
        }
    } else {
        onResult(null)
    }*/
}