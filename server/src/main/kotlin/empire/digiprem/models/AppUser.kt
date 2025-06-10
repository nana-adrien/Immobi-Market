package empire.digiprem.models

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@Entity(name = "app_user")
class AppUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long =0 ,
    private var username: String = "",
    private var password: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var enableTwoFactorAuth: Boolean = false,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, targetEntity = UserRole::class)
    var roles: Collection<UserRole> = ArrayList()
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return roles.stream()
            .map { role: UserRole -> SimpleGrantedAuthority(role.name) }
            .toList()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return username!!
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun setPassword(password: String) {
        this.password = password
    }
}
