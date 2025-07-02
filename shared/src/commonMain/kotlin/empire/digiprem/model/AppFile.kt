package empire.digiprem.model

import empire.digiprem.enums.FileUriTypeEnum

data class AppFile(
   val byteArray: ByteArray?,
   val name: String,
   val mimeType: FileUriTypeEnum,
   val mimeTypeDescriptor: String,
   val path: String? = null
)
