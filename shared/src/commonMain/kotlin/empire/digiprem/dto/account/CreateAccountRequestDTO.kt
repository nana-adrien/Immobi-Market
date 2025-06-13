package empire.digiprem.dto.account

import empire.digiprem.enums.Gender
import empire.digiprem.model.AppFile
import io.konform.validation.Validation
import io.konform.validation.constraints.notBlank

data class CreateAccountRequestDTO(
    val lastName: String = "",
    val firstName: String = "",
    val gender: Gender? = null,
    val profileUrl: String? = null,
    val phone: String = "",
    val countryCode: String = "",
    val enabledTwoFactorAuth: Boolean = false,
    val region: String = "",
    val city: String = "",
    val district: String = "",
    )

val createAccountRequestDTOValidation=Validation<CreateAccountRequestDTO>{
    CreateAccountRequestDTO::lastName  {
        notBlank()
    }
    CreateAccountRequestDTO::firstName  {
        notBlank()
    }
    CreateAccountRequestDTO::district  {
        notBlank()
    }
    CreateAccountRequestDTO::city  {
        notBlank()
    }
    CreateAccountRequestDTO::district  {
        notBlank()
    }
}