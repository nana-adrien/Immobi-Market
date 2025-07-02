package empire.digiprem.presentation.components.forms

import androidx.compose.runtime.Composable
import empire.digiprem.app.config.Log
import empire.digiprem.enums.FileUriTypeEnum
import empire.digiprem.model.AppFile
import kotlinx.browser.document
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Int8Array
import org.khronos.webgl.get
import org.w3c.dom.HTMLInputElement
import org.w3c.files.File
import org.w3c.files.FileReader
import org.w3c.files.get

@Composable
actual fun PickStorageFiles(onResult: (List<AppFile>) -> Unit): Execute {

    return object : Execute {
        override fun execute() {
            val inputForm = document.createElement("input") as HTMLInputElement
            inputForm.type = "file"
            inputForm.accept = "*/*"
            inputForm.multiple = true
            inputForm.onchange = {
                val files = inputForm.files
                val appFiles = mutableListOf<AppFile>()
                if (files != null) {
                    for (i in 0 until files.length) {
                        val file = files[i] ?: continue
                        handleImageFile(file) { byteArray ->
                            if (byteArray != null) {
                                appFiles.add(
                                    AppFile(
                                        byteArray = byteArray,
                                        name = file.name,
                                        mimeType = FileUriTypeEnum.fromExtension(file.type),
                                        mimeTypeDescriptor = file.type,
                                        path = null
                                    )
                                )
                            }
                        }
                    }
                } else {
                    appFiles+=listOf()
                }
                onResult(appFiles)
            }
            inputForm.click()
        }
    }
}

@Composable
actual fun PickStorageFile(
    input: String,
    onResult: (AppFile?) -> Unit
): Execute {
    return object : Execute {
        override fun execute() {
            val inputForm = document.createElement("input") as HTMLInputElement
            inputForm.type = "file"
            inputForm.accept = input
            inputForm.multiple = false
            inputForm.onchange = {
                val file = inputForm.files?.item(0)
                if (file != null) {
                    //for (i in 0 until files.length) {
                    //val file = files[i]!!
                    handleImageFile(file) {
                        AppFile(
                            byteArray = it,
                            name = file.name,
                            mimeType = FileUriTypeEnum.fromExtension(file.type),
                            mimeTypeDescriptor = file.type,
                            path = null
                        )
                    }
                    // }
                }
            }
            inputForm.click()
        }
    }
}




fun handleImageFile(file: File, onResult: (ByteArray?) -> Unit) {
    val reader = FileReader()

    reader.onload = {
        val result = reader.result
        if (result is ArrayBuffer) {
            val int8Array = Int8Array(result)
            val byteArray = ByteArray(int8Array.length) { i ->
                int8Array[i]
            }
            onResult(byteArray)
        } else {
            print("Résultat inattendu : ${result?.toString()}")
        }
    }

    reader.onerror = {
        print("Erreur de lecture du fichier : ${reader.error}")
    }

    reader.readAsArrayBuffer(file)
}

actual fun TakePicture(onResult: (AppFile?) -> Unit): Execute {
    return object : Execute {
        override fun execute() {
            Log.i("TakePicture", "This feature is not implemented for web.")
        }
    }
}