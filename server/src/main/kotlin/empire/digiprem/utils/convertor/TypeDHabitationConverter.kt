package empire.digiprem.utils.convertor

import empire.digiprem.enums.TypeDHabitation
import jakarta.persistence.AttributeConverter

class TypeDHabitationConverter: AttributeConverter<TypeDHabitation, Int> {
    override fun convertToDatabaseColumn(attribute: TypeDHabitation?): Int {
        return attribute?.code ?: 0
    }

    override fun convertToEntityAttribute(dbData: Int?): TypeDHabitation {
        return TypeDHabitation.values().find { it.code == dbData } ?: TypeDHabitation.NONE
    }
}