package empire.digiprem.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
@JvmRecord
data class AuthReqDto(
    @NotBlank(message = "ce champs ne peut pas etre vide ")
    val identity: String,
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial"
    )
    @NotBlank(message = "ce champs ne peut pas etre vide ")
    val password: String,
    val isEmail: Boolean,
    val phoneNumberCountryCode: String,
)

