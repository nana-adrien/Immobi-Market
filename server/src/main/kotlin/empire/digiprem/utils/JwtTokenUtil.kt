package empire.digiprem.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import empire.digiprem.models.UserRole
import empire.digiprem.models.Users
import empire.digiprem.service.IUserDetailService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function

@Service
class JwtTokenUtil(private val userDetailService: IUserDetailService) {
    //@Value("${jwt.secret-key}")
    private val SECRET_KEY = "bonjou le monde"

    // @Value("${jwt.expiration}")
    private val EXPIRATION = 5 * 60 * 1000L
    private val algorithm: Algorithm = Algorithm.HMAC256(SECRET_KEY)

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(userDetails, java.util.Map.of())
    }

    private fun generateToken(userDetails: UserDetails, claims: Map<String, String>): String {
        //  Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        val jwtBuilder = JWT.create()
            .withSubject(userDetails.username)
            .withIssuer("")
            .withClaim(
                "roles",
                userDetails.authorities.map { obj: GrantedAuthority -> obj.authority }.toList()
            )
            .withIssuedAt(Date(System.currentTimeMillis()))
            .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION))
        if (userDetails is Users) {
            jwtBuilder.withSubject(userDetails.email)
            jwtBuilder.withClaim("identity", userDetails.email)
        }
        claims.forEach { (name: String?, value: String?) -> jwtBuilder.withClaim(name, value) }
        return jwtBuilder.sign(algorithm)
    }

    @Throws(TokenExpiredException::class)
    fun decodedJWT(token: String?): DecodedJWT {
        //   Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        val verifier: JWTVerifier = JWT.require(algorithm).build()
        return verifier.verify(token)
    }

    fun <T> getClaims(name: String, claimsResolver: Function<Map<String, Claim>, T>): T {
        val claim = getAllClaims(name)
        return claimsResolver.apply(claim)
    }

    fun getUsername(token: String): String {
        return getClaims(
            token
        ) { claims: Map<String, Claim> ->
            claims["sub"]!!
                .asString()
        }
    }

    fun getIdentity(token: String): String {
        return getClaims(
            token
        ) { claims: Map<String, Claim> ->
            claims["identity"]!!
                .asString()
        }
    }

    fun getExpiration(token: String): Date {
        return getClaims(
            token
        ) { claims: Map<String, Claim> ->
            claims["exp"]!!
                .asDate()
        }
    }

    fun getAllClaims(token: String): Map<String, Claim> {
        return decodedJWT(token).claims
    }

    fun getUserRoles(token: String): List<UserRole> {
        return getClaims<List<UserRole>>(
            token,
            Function<Map<String, Claim>, List<UserRole>> { claims: Map<String, Claim> ->
                claims["roles"]!!
                    .asList(
                        UserRole::class.java
                    )
            })
    }

    fun isTokenExpired(token: String): Boolean {
        return try {
            decodedJWT(token).expiresAt.before(Date(System.currentTimeMillis()))
        } catch (e: TokenExpiredException) {
            true
        }
    }

    fun isTokenValid(token: String, userDetails: Users): Boolean {
        return try {
            decodedJWT(token).subject == userDetails.email && !isTokenExpired(token)
        } catch (e: TokenExpiredException) {
            true
        }
    }
}