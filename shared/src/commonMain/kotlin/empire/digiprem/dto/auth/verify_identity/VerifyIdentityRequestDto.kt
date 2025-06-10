package empire.digiprem.dto.auth.verify_identity

import io.konform.validation.Validation
import io.konform.validation.constraints.maxLength
import io.konform.validation.constraints.minLength
import io.konform.validation.constraints.notBlank
import kotlinx.serialization.Serializable

@Serializable
data class VerifyIdentityRequestDto(
    val email: String="",
    val pinCode: String="",
    val isEmailIdentity: Boolean=false,
    val phoneNumber: String="",
    val countryCode: String="",
    val regionCode: String=""
)

val verifyIdentityValidation=Validation<VerifyIdentityRequestDto>{
    VerifyIdentityRequestDto::pinCode {
        maxLength(6)
        minLength(6)
        notBlank().hint("ce champs ne peut pas etre vide ")
    }
    VerifyIdentityRequestDto::email {
        notBlank().hint("ce champs ne peut pas etre vide ")
    }
}