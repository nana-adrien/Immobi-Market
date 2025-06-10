package empire.digiprem.enums

internal interface ExtantionFileName {
    var value: String
}

enum class FileUriTypeEnum : ExtantionFileName {
    // IMAGES
    IMAGES {
        override var value = "image/*"
    },
    IMAGE_JPEG {
        override var value = "image/jpeg"
    },
    IMAGE_PNG {
        override var value = "image/png"
    },
    IMAGE_GIF {
        override var value = "image/gif"
    },
    IMAGE_WEBP {
        override var value = "image/webp"
    },
    IMAGE_BMP {
        override var value = "image/bmp"
    },
    IMAGE_SVG {
        override var value = "image/svg+xml"
    },

    // VIDEOS

    VIDEOS {
        override var value = "video/*"
    },
    VIDEO_MP4 {
        override var value = "video/mp4"
    },
    VIDEO_WEBM {
        override var value = "video/webm"
    },
    VIDEO_MPEG {
        override var value = "video/mpeg"
    },
    VIDEO_AVI {
        override var value = "video/x-msvideo"
    },
    VIDEO_3GP {
        override var value = "video/3gpp"
    },

    // AUDIOS

    AUDIOS {
        override var value = "audio/*"
    },
    AUDIO_MP3 {
        override var value = "audio/mpeg"
    },
    AUDIO_WAV {
        override var value = "audio/wav"
    },
    AUDIO_OGG {
        override var value = "audio/ogg"
    },

    // PDF
    PDF {
        override var value = "application/pdf"
    },

    // DOCUMENTS
    DOCUMENTS {
        override var value = "application/*"
    },
    DOC {
        override var value = "application/msword"
    },
    DOCX {
        override var value = "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    },
    PPT {
        override var value = "application/vnd.ms-powerpoint"
    },
    PPTX {
        override var value = "application/vnd.openxmlformats-officedocument.presentationml.presentation"
    },
    XLS {
        override var value = "application/vnd.ms-excel"
    },
    XLSX {
        override var value = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    },
    TXT {
        override var value = "text/plain"
    },
    RTF {
        override var value = "application/rtf"
    },
    CSV {
        override var value = "text/csv"
    },
    HTML {
        override var value = "text/html"
    },
    JSON {
        override var value = "application/json"
    },



    // ARCHIVES
    ZIP {
        override var value = "application/zip"
    },
    RAR {
        override var value = "application/vnd.rar"
    },
    TAR {
        override var value = "application/x-tar"
    },
    GZIP {
        override var value = "application/gzip"
    },

    // AUTRES
    ORDER {
        override var value = "application/vnd.order"
    }, // personnalisé
    NOT_FOUND {
        override var value = "application/octet-stream"
    }; // fallback

    fun getName():String{
        return name.split("_").last()
    }

    companion object {
        fun fromExtension(ext: String): FileUriTypeEnum {
            return when (ext.lowercase()) {
                "jpg", "jpeg" -> IMAGE_JPEG
                "png" -> IMAGE_PNG
                "gif" -> IMAGE_GIF
                "webp" -> IMAGE_WEBP
                "bmp" -> IMAGE_BMP
                "svg" -> IMAGE_SVG

                "pdf" -> PDF
                "doc" -> DOC
                "docx" -> DOCX
                "ppt" -> PPT
                "pptx" -> PPTX
                "xls" -> XLS
                "xlsx" -> XLSX
                "txt" -> TXT
                "rtf" -> RTF
                "csv" -> CSV
                "html", "htm" -> HTML
                "json" -> JSON

                "mp4" -> VIDEO_MP4
                "webm" -> VIDEO_WEBM
                "mpeg", "mpg" -> VIDEO_MPEG
                "avi" -> VIDEO_AVI
                "3gp" -> VIDEO_3GP

                "mp3" -> AUDIO_MP3
                "wav" -> AUDIO_WAV
                "ogg" -> AUDIO_OGG

                "zip" -> ZIP
                "rar" -> RAR
                "tar" -> TAR
                "gz", "gzip" -> GZIP
                else -> NOT_FOUND
            }
        }
    }
}
