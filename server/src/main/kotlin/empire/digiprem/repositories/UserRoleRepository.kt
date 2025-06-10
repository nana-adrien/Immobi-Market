package empire.digiprem.repositories

import empire.digiprem.models.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRoleRepository : JpaRepository<UserRole, Long> {
    fun findRoleByName(name: String): Optional<UserRole>
}
