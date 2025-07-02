package empire.digiprem.models.database

import empire.digiprem.enums.Role
import empire.digiprem.utils.convertor.RoleConverter
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity(name ="app_user")
data class User @OptIn(ExperimentalUuidApi::class) constructor(
    @Id val id: UUID = UUID.randomUUID(),
    val email:String,
    val motDePasse:String,
    @Convert(converter = RoleConverter::class)
    val role: Role,
): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return listOf(role.let { role -> GrantedAuthority { role.name } }).toMutableList()
    }
    override fun getPassword(): String {
        return motDePasse
    }
    override fun getUsername(): String {
       return email
    }

    fun peutPublierUnBien(): Boolean {
        return role == Role.ADMINISTRATEUR || role == Role.PROPRIETAIRE
    }

}