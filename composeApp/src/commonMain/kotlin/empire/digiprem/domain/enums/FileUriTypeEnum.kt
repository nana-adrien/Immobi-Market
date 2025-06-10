package octopusfx.client.mobile.core.domain.enums

internal interface ExtantionFileName{ var value:String  }
enum class FileUriTypeEnum: octopusfx.client.mobile.core.domain.enums.ExtantionFileName {
    IMAGE {
        override var value: String= name
    },
    PDF {
        override var value: String= name
    },
    DOC {
        override var value: String= name
    },
    VIDEO {
        override var value: String= name
    },
    ORDER {
        override var value: String= name
    },
    NOT_FOUND {
        override var value: String= name
    },
}