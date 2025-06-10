package empire.digiprem.services

import empire.digiprem.repositories.UsersRepository
import org.springframework.stereotype.Service

@Service
class EmailService {
    private val usersRepository: UsersRepository? = null

    fun validateEmail(email: String?): Boolean {
        val regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return email != null && email.matches(regex.toRegex())
    }

    fun isEmailValid(email: String): Boolean {
        return usersRepository?.findAll()?.any { user -> user.email.equals(email) }?:false
    }

    fun sendPasswordResetEmail(email: String?) {}
    fun sendCustomEmail(to: String?, subject: String?, content: String?) {}
}