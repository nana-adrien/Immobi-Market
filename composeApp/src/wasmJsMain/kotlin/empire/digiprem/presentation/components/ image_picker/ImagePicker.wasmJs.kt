package empire.digiprem.presentation.components.` image_picker`

import kotlinx.browser.document
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Int8Array
import org.khronos.webgl.get
import org.w3c.dom.HTMLInputElement
import org.w3c.files.File
import org.w3c.files.FileReader

actual fun pickImage(onResult: (ImageData?) -> Unit) {
    pickFile(onResult)
}


fun pickFile(onResult: (ImageData?) -> Unit) {
    val input = document.createElement("input") as HTMLInputElement
    input.type = "file"
    input.accept = "image/*"
    input.multiple = false
    input.onchange = {
        val file = input.files?.item(0)
        if (file != null) {
            //for (i in 0 until files.length) {
                //val file = files[i]!!
                handleImageFile(file){
                    ImageData(
                        byteArray = it,
                        name = file.name,
                        mimeType = file.type,
                        path =null
                    )


                }
           // }
        }
    }
    /*input.addEventListener("change", {
        val file = input.files?.item(0)
        callback(file)
    })*/
    input.click()
}
fun handleImageFile(file: File,onResult: (ByteArray?) -> Unit) {
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