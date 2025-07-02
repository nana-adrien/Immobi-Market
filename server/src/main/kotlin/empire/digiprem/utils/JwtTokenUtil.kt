@file:OptIn(ExperimentalUuidApi::class)

package empire.digiprem.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import empire.digiprem.enums.TokenEnum
import empire.digiprem.models.UserRole
import empire.digiprem.models.database.User
import empire.digiprem.models.database.UserToken
import empire.digiprem.repositories.database.IUserTokenRepository
import empire.digiprem.service.IUserDetailService2
import empire.digiprem.services.UserDetailService2Impl
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.function.Function
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Service
class JwtTokenUtil(userDetails: UserDetailService2Impl) {
    //@Value("${jwt.secret-key}")
    private val SECRET_KEY = "bonjou le monde"

    // @Value("${jwt.expiration}")
    private val EXPIRATION = 10 * 60 * 1000L
    private val REFRESH_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L
    private val algorithm: Algorithm = Algorithm.HMAC256(SECRET_KEY)

    fun generateAccessToken(userDetails: UserDetails, id: String): String {

        return generateAccessToken(id, userDetails, java.util.Map.of())
    }

    private fun generateAccessToken(id: String, userDetails: UserDetails, claims: Map<String, String>): String {
        //  Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        val jwtBuilder = JWT.create()
            .withSubject(userDetails.username)
            //.withIssuer("")
            .withClaim("roles", userDetails.authorities.map { obj: GrantedAuthority -> obj.authority }.toList())
            .withClaim("id", id)
            .withClaim("type", TokenEnum.ACCESS_TOKEN.name)
            .withIssuedAt(Date(System.currentTimeMillis()))
            .withExpiresAt(Date(System.currentTimeMillis().plus(EXPIRATION)))
        /*if (userDetails is User) {
            jwtBuilder.withSubject(userDetails.email)
            jwtBuilder.withClaim("identity", userDetails.email)
        }*/
        claims.forEach { (name: String?, value: String?) -> jwtBuilder.withClaim(name, value) }
        return jwtBuilder.sign(algorithm)
    }

    fun generateRefreshToken(userDetails: UserDetails, id: String): String {
        //  Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        val jwtBuilder = JWT.create()
            .withSubject(userDetails.username)
            .withIssuedAt(Date(System.currentTimeMillis()))
            .withExpiresAt(Date(System.currentTimeMillis().plus(REFRESH_TOKEN_EXPIRATION_TIME)))
            .withClaim("id", id)
            .withClaim("type", TokenEnum.REFRESH_TOKEN.name)
        return jwtBuilder.sign(algorithm)
    }


    fun decodedJWT(token: String?): DecodedJWT {
        try {
            //   Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            val verifier: JWTVerifier = JWT.require(algorithm).build()
            return verifier.verify(token)
        } catch (e: Exception) {
            throw RuntimeException("is invalid token")
        }

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

    fun getId(token: String): UUID {
        return UUID.fromString(
            getClaims(
                token
            ) { claims: Map<String, Claim> ->
                claims["id"]!!.asString()
            }
        )
    }

 /*   fun getIdentity(token: String): String {
        return getClaims(
            token
        ) { claims: Map<String, Claim> ->
            claims["identity"]!!
                .asString()
        }
    }*/

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

    fun getType(token: String): TokenEnum {
        return getClaims(token, { claims: Map<String, Claim> -> TokenEnum.valueOf(claims["type"]!!.asString()) })
    }

    fun isTokenExpired(token: String): Boolean {
        return try {
            decodedJWT(token).expiresAt.before(Date(System.currentTimeMillis()))
        } catch (e: TokenExpiredException) {
            println("Token is expired: ${e.message}")
            true
        }
    }

    fun isTokenValid(token: String, userDetails: User): Boolean {
        return try {
            decodedJWT(token).subject == userDetails.email && !isTokenExpired(token)
        } catch (e: TokenExpiredException) {
            println("Token is not  valid: ${e.message}")
            true
        }
    }
}