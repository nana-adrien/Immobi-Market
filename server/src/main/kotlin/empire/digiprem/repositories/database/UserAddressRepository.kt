package empire.digiprem.repositories.database

import empire.digiprem.models.database.UserAddress
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Repository
interface UserAddressRepository : JpaRepository<UserAddress, UUID> {

}