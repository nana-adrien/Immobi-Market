package empire.digiprem.utils.convertor

import empire.digiprem.enums.Role
import jakarta.persistence.AttributeConverter

class RoleConverter:AttributeConverter<Role,Int> {
    override fun convertToDatabaseColumn(attribute: Role?): Int {
        return attribute?.code ?: Role.DEMANDEUR.code
    }
    override fun convertToEntityAttribute(dbData: Int?): Role {
        return dbData?.let {code-> Role.entries.first { it.code==code }}?:Role.DEMANDEUR
    }
}