package empire.digiprem.utils.convertor

import empire.digiprem.enums.NotificationCanal
import jakarta.persistence.AttributeConverter

class NotificationCanalConverter : AttributeConverter<NotificationCanal, Int> {
    override fun convertToDatabaseColumn(attribute: NotificationCanal?): Int {
        return attribute?.code ?: NotificationCanal.EMAIL.code
    }
    override fun convertToEntityAttribute(dbData: Int?): NotificationCanal {
        return dbData?.let { NotificationCanal.entries.first { it.code==dbData } } ?: NotificationCanal.EMAIL
    }
}