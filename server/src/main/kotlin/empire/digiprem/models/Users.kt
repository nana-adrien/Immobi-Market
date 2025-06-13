package empire.digiprem.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.*


@Entity(name = "user_app")
data class Users(
    @Id
    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    var userPassword: String = "",
    val firstName: String = "",
    val profileUrl: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    @OneToOne
    val settings: UserSettings? = null,
    @OneToOne
    val userPhoneNumber: UserPhoneNumber? = null,
    @OneToOne
    val userAddress: UserAddress? = null,
    val enableTwoFactorAuth: Boolean = false,
    val isEmailVerified: Boolean = false,
    val isPhoneVerified: Boolean = false,
    val isComplete: Boolean = false,
    val isOnline: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var lastConnectionTime: LocalDateTime? = null,
) : UserDetails {


    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf()
    }

    override fun getPassword(): String {
        return userPassword
    }

    override fun getUsername(): String {
        return name
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
}
