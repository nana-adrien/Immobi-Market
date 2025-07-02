package empire.digiprem.repositories

import empire.digiprem.models.database.UserPhoneNumber
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserPhoneNumberRepository : JpaRepository<UserPhoneNumber?, UUID?> {
    fun findByCountryCodeAndPhoneNumber(CountryCode: String, phoneNumber: String): Optional<UserPhoneNumber>
}
