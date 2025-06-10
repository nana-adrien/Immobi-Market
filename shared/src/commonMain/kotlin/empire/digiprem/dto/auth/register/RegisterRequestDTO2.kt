package empire.digiprem.dto.auth.register

import io.konform.validation.Validation
import io.konform.validation.constraints.notBlank
import io.konform.validation.constraints.pattern
import kotlinx.serialization.Serializable


@Serializable
data class RegisterRequestDTO2(
    val email: String="",
    val password:  String="",
    val confirmPassword: String="",
    val IsEmailIdentity: Boolean=false,
    val phoneNumber: String="",
    val countryCode: String="",
    val regionCode: String=""
)
val registerRequestDTO2Validation = Validation {
    RegisterRequestDTO2::email {
        notBlank()
        pattern("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$").hint("l'adresse email n'est pas valide")
    }

    RegisterRequestDTO2::password  {
        notBlank().hint("ce champs ne peut pas etre vide ")
        pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$").hint( "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial")
    }
}