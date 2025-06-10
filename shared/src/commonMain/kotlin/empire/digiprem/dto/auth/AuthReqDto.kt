package empire.digiprem.dto.auth;

import io.konform.validation.Validation
import io.konform.validation.constraints.notBlank
import io.konform.validation.constraints.pattern

data class AuthReqDto(
    val identity: String,
    val password: String,
    val isEmail: Boolean,
    val phoneNumberCountryCode: String,
)

val authReqDtoValidation=Validation<AuthReqDto>{
    AuthReqDto::identity {
        notBlank().hint("ce champs ne peut pas etre vide ")
    }
    AuthReqDto::password{
        notBlank().hint("ce champs ne peut pas etre vide ")
        pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$").hint( "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial")
    }
}

