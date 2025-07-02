package empire.digiprem.dto.account

import empire.digiprem.enums.Gender
import empire.digiprem.model.Adresse
import empire.digiprem.model.AppFile
import empire.digiprem.model.PhoneNumber
import io.konform.validation.Validation
import io.konform.validation.constraints.notBlank
import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountRequestDTO(
    val lastName: String = "",
    val firstName: String = "",
    val gender: Gender = Gender.MALE,
    val contact: PhoneNumber? = null,
    val adresse: Adresse? = null,
    val enabledTwoFactorAuth: Boolean = false,
    )

val createAccountRequestDTOValidation = Validation<CreateAccountRequestDTO> {
    CreateAccountRequestDTO::lastName  {
        notBlank()
    }
    CreateAccountRequestDTO::firstName  {
        notBlank()
    }
}