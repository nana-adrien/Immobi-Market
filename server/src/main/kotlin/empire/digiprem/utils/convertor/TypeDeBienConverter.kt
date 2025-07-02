package empire.digiprem.utils.convertor

import empire.digiprem.enums.TypeDeBien
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter


@Converter
public class  TypeDeBienConverter: AttributeConverter<TypeDeBien, Int> {
    override fun convertToDatabaseColumn(attribute: TypeDeBien?): Int {
        return attribute?.code ?: 0
    }

    override fun convertToEntityAttribute(dbData: Int?): TypeDeBien {
        return TypeDeBien.values().find { it.code == dbData } ?: TypeDeBien.APPARTEMENT
    }

}

