package empire.digiprem.repositories

import empire.digiprem.models.UserPhoneNumber
import empire.digiprem.models.Users
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface UsersRepository : JpaRepository<Users, UUID> {
    fun findByEmail(email: String): Optional<Users>
    fun findByUserPhoneNumber(userPhoneNumber: UserPhoneNumber): Optional<Users>
    fun findByPhoneNumber(phoneNumber: String): Optional<Users>
    fun findByName(username: String): Optional<Users>
}
