package empire.digiprem.models

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.*


@Entity
class Attachment(
    @Id
    private val id: UUID = UUID.randomUUID(),
    private
    var title: String = "",
    private
    var type: TypeEnum = TypeEnum.DOCUMENT,
    @ManyToOne(fetch = FetchType.LAZY)
    private var message: Message,
    private var description: String="",
    private var url: String=""
) {
    enum class TypeEnum {
        DOCUMENT,
        VIDEO,
        AUDIO,
        IMAGE
    }
}
