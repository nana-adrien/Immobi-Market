package empire.digiprem.utils.convertor

import empire.digiprem.enums.TypeDoffre
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class TypeDoffreConverter: AttributeConverter<TypeDoffre, Int> {
    override fun convertToDatabaseColumn(attribute: TypeDoffre?): Int {
        return attribute?.code ?: 0
    }
    override fun convertToEntityAttribute(dbData: Int?): TypeDoffre {
        return TypeDoffre.values().find { it.code == dbData } ?: TypeDoffre.NONE
    }
}