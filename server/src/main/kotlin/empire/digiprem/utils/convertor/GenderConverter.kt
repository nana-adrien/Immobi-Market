package empire.digiprem.utils.convertor

import empire.digiprem.enums.Gender
import jakarta.persistence.AttributeConverter

class GenderConverter:AttributeConverter<Gender,String> {
    override fun convertToDatabaseColumn(attribute: Gender?): String? {
        return attribute?.name?:Gender.MALE.name
    }

    override fun convertToEntityAttribute(dbData: String?): Gender? {
        return dbData?.let { Gender.entries.first { it.name==dbData }}?:Gender.MALE
    }
}