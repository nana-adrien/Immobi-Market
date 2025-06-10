package empire.digiprem.models

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class EmailAndPasswordAuthenticationToken : AbstractAuthenticationToken {
    /*pour fonctionner correctement avec Spring Security, tu dois stocker l'email et le mot de passe,
        puis les retourner dans getPrincipal() et getCredentials().*/
    private val principal: UserDetails?
    private var credentials:  String

    constructor(principal:  UserDetails, credentials:  String) : super(null) {
        this.principal = principal
        this.credentials = credentials
        isAuthenticated = false //pas encore authentifier
    }

    constructor(principal:  UserDetails, credentials:  String, authorities: Collection<GrantedAuthority?>?) : super(authorities) {
        this.principal = principal
        this.credentials = credentials
        isAuthenticated = true
    }

    override fun getCredentials():  String {
        return this.credentials
    }

    override fun getPrincipal():  UserDetails? {
        return this.principal
    }

    override fun eraseCredentials() {
        super.eraseCredentials()
        // securiter : on efface le mot de passe
        this.credentials = ""
    }
}
