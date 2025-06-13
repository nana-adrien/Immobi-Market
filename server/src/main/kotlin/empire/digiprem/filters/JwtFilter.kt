package empire.digiprem.filters

import empire.digiprem.models.IdentityAndPasswordAuthenticationToken
import empire.digiprem.models.Users
import empire.digiprem.service.IUserDetailService
import empire.digiprem.utils.JwtTokenUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtFilter(private val userDetailsService: IUserDetailService, private val jwtTokenUtil: JwtTokenUtil) :
    OncePerRequestFilter() {
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
            username = jwtTokenUtil.getIdentity(jwtToken)
        }
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
          //  JwtFilter.log.info("Security context was null, so authorizing user")
          //  JwtFilter.log.info("User details request received for user: {}", username)
            val userDetails = userDetailsService.loadUserByIdentity(username) as Users

            println("User details request received for user: ${userDetails.username}")

            if (jwtTokenUtil.isTokenValid(jwtToken?:"", userDetails)) {
                val identityAndPasswordAuthenticationToken =
                    IdentityAndPasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                identityAndPasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                println("User details request received for identityAndPasswordAuthenticationToken: ${identityAndPasswordAuthenticationToken.name}")
                SecurityContextHolder.getContext().authentication = identityAndPasswordAuthenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}