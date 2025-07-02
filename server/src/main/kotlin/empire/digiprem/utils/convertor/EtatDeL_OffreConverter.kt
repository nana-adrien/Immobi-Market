package empire.digiprem.utils.convertor

import empire.digiprem.enums.EtatDeL_Offre
import jakarta.persistence.AttributeConverter

class EtatDeL_OffreConverter:AttributeConverter<EtatDeL_Offre,Int> {
    override fun convertToDatabaseColumn(attribute: EtatDeL_Offre?): Int {
        return attribute?.code ?: 0
    }

    override fun convertToEntityAttribute(dbData: Int?): EtatDeL_Offre {
       return EtatDeL_Offre.entries.first { e -> e.code == dbData }
    }
}