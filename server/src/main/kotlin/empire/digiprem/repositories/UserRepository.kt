package  empire.digiprem.repositories

import empire.digiprem.models.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<AppUser, Long> {
    fun findByUsername(username: String): Optional<AppUser>
    fun findByEmail(email: String): Optional<AppUser>
    fun findByPhoneNumber(phone: String): Optional<AppUser>
}
