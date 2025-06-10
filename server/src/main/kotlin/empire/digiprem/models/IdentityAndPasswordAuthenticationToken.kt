package empire.digiprem.models

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority


class IdentityAndPasswordAuthenticationToken : AbstractAuthenticationToken {
    private val principal: Any
    private var credentials: Any?

    constructor(principal: Any, credentials: Any?) : super(null) {
        this.principal = principal
        this.credentials = credentials
        isAuthenticated = false
    }

    constructor(principal: Any, credentials: Any?, authorities: Collection<GrantedAuthority?>?) : super(authorities) {
        this.principal = principal
        this.credentials = credentials
        isAuthenticated = true
    }

    override fun getCredentials(): Any? {
        return this.credentials
    }

    override fun getPrincipal(): Any {
        return this.principal
    }

    override fun eraseCredentials() {
        super.eraseCredentials()
        this.credentials = null
    }
}
