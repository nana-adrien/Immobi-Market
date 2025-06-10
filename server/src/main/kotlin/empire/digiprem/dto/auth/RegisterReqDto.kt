package empire.digiprem.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import javax.swing.text.StyledEditorKit.BoldAction

@JvmRecord
public data class RegisterReqDto(
        val username:String,
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial"
        )
        val password:String,
        @NotBlank(message = "l'email ne peut pas etre vide ")
        @Email(message = "veiller saisir un email valide ")
        val email:String,
        @NotBlank(message = "l'le numero de telephone ne peut pas etre vide ")
        val phoneNumber:String,
        val phoneNumberCountryCode:String,
        val enableTwoFactorAuth:Boolean,
        val roles:List<String>) {
}
