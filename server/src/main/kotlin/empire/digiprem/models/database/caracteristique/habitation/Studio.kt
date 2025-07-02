package empire.digiprem.models.database.caracteristique.habitation

import empire.digiprem.enums.TypeDHabitation
import empire.digiprem.models.database.caracteristique.Habitation
import empire.digiprem.utils.convertor.TypeDHabitationConverter
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.PrimaryKeyJoinColumn

@Entity
@PrimaryKeyJoinColumn(name = "id")
data class Studio(
    override val superficie: Double,
    @Convert(converter = TypeDHabitationConverter::class)
    override val typeDHabitation: TypeDHabitation
) : Habitation() {
    override val nombreDePieces: Int
        get() = if (typeDHabitation == TypeDHabitation.MODERNE) 2 else 1
}
