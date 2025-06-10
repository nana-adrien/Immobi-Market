package empire.digiprem.model

 data class AppFile(
    val byteArray: ByteArray?,
    val name: String,
    val mimeType: FileUriTypeEnum,
    val mimeTypeDescriptor: String,
    val path: String? = null
)

class FileUriTypeEnum {

}
