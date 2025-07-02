package empire.digiprem.filters

import empire.digiprem.models.database.User
import empire.digiprem.service.IUserDetailService2
import empire.digiprem.services.auth.UserTokenService
import empire.digiprem.utils.JwtTokenUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.server.ResponseStatusException
import java.io.IOException
import kotlin.uuid.ExperimentalUuidApi

@Component
class JwtFilter(
    private val userTokenService: UserTokenService,
    private val userDetailsService: IUserDetailService2,
    private val jwtTokenUtil: JwtTokenUtil) :
    OncePerRequestFilter() {
    @OptIn(ExperimentalUuidApi::class)
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")
        var username: String? = null
        var jwtToken: String? = null

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7)
            username = jwtTokenUtil.getUsername(jwtToken)
            if (jwtTokenUtil.getType(token = jwtToken).isRefreshToken()) {
                throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token type")
            }
        }
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByIdentity(username) as User
            var userToken= userTokenService.getUserTokenByUserAndId(userDetails,jwtTokenUtil.getId(jwtToken!!))
                ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token ")

            if (jwtTokenUtil.isTokenValid(jwtToken?:"", userDetails)) {
                val identityAndPasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                identityAndPasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                println("User details request received for identityAndPasswordAuthenticationToken: ${(identityAndPasswordAuthenticationToken.principal as User).email}")
                SecurityContextHolder.getContext().authentication = identityAndPasswordAuthenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}