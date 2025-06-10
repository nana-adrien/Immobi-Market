package empire.digiprem.dto.auth;

import io.konform.validation.Validation
import io.konform.validation.constraints.notBlank
import io.konform.validation.constraints.pattern


public data class RegisterReqDto(
        val username:String,
        val password:String,
        val email:String,
        val phoneNumber:String,
        val phoneNumberCountryCode:String,
        val enableTwoFactorAuth:Boolean,
        val roles:List<String>) {
}

val registerReqDtoValidation= Validation<RegisterReqDto>{
        RegisterReqDto::username {
                notBlank().hint("ce champs ne peut pas etre vide ")
        }
        RegisterReqDto::password{
                notBlank().hint("ce champs ne peut pas etre vide ")
                pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$").hint( "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial")
        }
        RegisterReqDto::phoneNumber{
                notBlank().hint("ce champs ne peut pas etre vide ")
        }
}