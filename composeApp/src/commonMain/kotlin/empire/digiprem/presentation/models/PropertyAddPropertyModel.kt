package empire.digiprem.presentation.models

import empire.digiprem.enums.TypeDeBien
import empire.digiprem.enums.TypeDoffre

data class PropertyAddPropertyModel(
    val isLoading: Boolean = false,
    val title: String="",
    val typeDoffre: TypeDoffre=TypeDoffre.A_VENDRE,
    val typeDeBien: TypeDeBien=TypeDeBien.MAISON
)