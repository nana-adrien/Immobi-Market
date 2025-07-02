package empire.digiprem.services.auth

import empire.digiprem.repositories.database.UsersRepository
import org.springframework.stereotype.Service

@Service
class PasswordService(
    val usersRepository: UsersRepository
) {

    fun forgotPassword(email: String) {
    }

    fun resetPassword(identity: String, newPassword: String) {
    }

    fun changePassword(userId: String, oldPwd: String, newPwd: String) {
    }

    fun validatePassword(password: String): Boolean {
        val regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
        if (!password.matches(regexp.toRegex())) {
            throw RuntimeException("Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial")
        }
        return true
    }
}