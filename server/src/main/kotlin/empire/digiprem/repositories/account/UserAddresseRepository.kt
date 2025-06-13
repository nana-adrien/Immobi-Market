package empire.digiprem.repositories.account

import empire.digiprem.models.UserAddress
import jakarta.persistence.Column
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Repository
interface UserAddressRepository : JpaRepository<UserAddress, Uuid> {
     fun findByContinentAndCountryAndCityAndRegionAndDistrict(
        continent: String,
        country: String,
        city: String,
        region: String,
        district: String
    ): Optional<UserAddress>
}