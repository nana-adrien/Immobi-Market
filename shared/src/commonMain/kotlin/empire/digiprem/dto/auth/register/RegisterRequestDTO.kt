package empire.digiprem.dto.auth.register

import io.konform.validation.Validation
import io.konform.validation.constraints.*
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDTO(
    val identity: String,
    val password: String,
    val confirmPassword: String,
    val isEmail: Boolean
)

val registerRequestDTOValidation = Validation<RegisterRequestDTO> {
    RegisterRequestDTO::identity {
        notBlank()
        pattern("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$").hint("l'adresse email n'est pas valide")
    }

    RegisterRequestDTO::password  {
        notBlank().hint("ce champs ne peut pas etre vide ")
        pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$").hint( "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial")
    }
}


