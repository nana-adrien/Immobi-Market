package empire.digiprem.presentation.components.forms

import androidx.compose.runtime.Composable
import empire.digiprem.app.config.Log
import empire.digiprem.enums.FileUriTypeEnum
import empire.digiprem.model.AppFile
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

@Composable
actual fun PickStorageFile(
    input: String,
    onResult: (AppFile?) -> Unit
): Execute {
    return object :Execute {
        override fun execute(input: String) {

            val chooser = JFileChooser().apply {
                fileFilter = FileNameExtensionFilter(input.replace("/*",""), "*.*")
            }
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                val file = chooser.selectedFile
                val bytes = file.readBytes()
                onResult(
                    AppFile(
                        name = file.name,
                        mimeType = FileUriTypeEnum.fromExtension(file.extension),
                        path = file.path,
                        mimeTypeDescriptor = file.extension,
                        byteArray = bytes
                    )
                )
            } else {
                onResult(null)
            }
        }

    }


}

@Composable
actual fun PickStorageFiles(onResult: (List<AppFile>) -> Unit): Execute {
    return object : Execute {
        override fun execute(input: String,) {
            val chooser = JFileChooser().apply {
                fileFilter = FileNameExtensionFilter(input.replace("/*",""), "*.*")
                isMultiSelectionEnabled = true
            }
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                val selectedFiles = chooser.selectedFiles
                val appFiles = selectedFiles.map { file ->
                    AppFile(
                        name = file.name,
                        mimeType = FileUriTypeEnum.fromExtension(file.extension),
                        path = file.path,
                        mimeTypeDescriptor = file.extension,
                        byteArray = file.readBytes()
                    )
                }
                onResult(appFiles)
            } else {
                onResult(emptyList())
            }
        }
    }
}

@Composable
actual fun TakePicture(onResult: (AppFile?) -> Unit): Execute {
    return object : Execute {
        override fun execute(input: String) {
            Log.i("TakePicture", "This feature is not implemented for desktop.")
        }
    }
}