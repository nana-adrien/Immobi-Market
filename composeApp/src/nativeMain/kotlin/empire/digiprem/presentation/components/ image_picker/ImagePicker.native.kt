package empire.digiprem.presentation.components.` image_picker`

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret
import platform.Foundation.NSData
import platform.UIKit.*
import platform.darwin.ByteVar
import platform.darwin.NSObject

actual fun pickImage(onResult: (ImageData?) -> Unit) {
    val picker = UIImagePickerController().apply {
        sourceType = UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypePhotoLibrary // PhotoLibrary
        delegate = object : NSObject(),
            UIImagePickerControllerDelegateProtocol,
            UINavigationControllerDelegateProtocol {
            override fun imagePickerController(
                picker: UIImagePickerController,
                didFinishPickingMediaWithInfo: Map<Any?, *>
            ) {

                val image = didFinishPickingMediaWithInfo[UIImagePickerControllerOriginalImage] as UIImage

                picker.dismissViewControllerAnimated(true) {
                    val data = image.let { UIImageJPEGRepresentation(it, 0.8) }

                    onResult(
                        ImageData(
                            byteArray = data?.toByteArray(),
                            name ="" ,
                            path = "",
                            mimeType =sourceType.name
                        )
                    )
                }
            }

            override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
                picker.dismissViewControllerAnimated(true) {
                    onResult(null)
                }
            }
        }
    }

    UIApplication.sharedApplication.keyWindow?.rootViewController?.presentedViewController()
        .apply { picker /*,true,null)*/ }
}

@OptIn(ExperimentalForeignApi::class)
fun NSData.toByteArray(): ByteArray {
    val size = this.length.toInt()
    val byteArray = ByteArray(size)

    memScoped {
        val bytes = this@toByteArray.bytes?.reinterpret<ByteVar>()
        if (bytes != null) {
            for (i in 0 until size) {
                byteArray[i] = bytes[i].toByte()
            }
        }

    }
    return byteArray
}