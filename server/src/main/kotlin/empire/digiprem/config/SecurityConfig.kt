package empire.digiprem.config;

import empire.digiprem.filters.JwtFilter;
import empire.digiprem.models.IdentityAndPasswordAuthenticationProvider;
import empire.digiprem.services.UserDetailService2Impl
import empire.digiprem.services.auth.UserTokenService
import empire.digiprem.utils.JwtTokenUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
public class SecurityConfig(
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsService: UserDetailService2Impl,
    private val userTokenService: UserTokenService,
    private val passwordEncoder: PasswordEncoder,
    private val customProvider: IdentityAndPasswordAuthenticationProvider
) {

    /* Cette annotation indique à Spring que cette méthode retourne un bean à gérer dans le contexte de l'application.
         Ici, on retourne un objet SecurityFilterChain, qui configure la sécurité HTTP de l'application.
        */
    @Bean
    public fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            /*  Désactive la protection CSRF (Cross-Site Request Forgery).
              ⚠️ Attention : c'est souvent utile pour les API REST ou WebSocket, mais en prod ou avec des formulaires, il faut être sûr de bien gérer les risques.
            */.csrf({ it.disable() })
            /*  Désactive la configuration CORS (Cross-Origin Resource Sharing).
             Cela permet des requêtes entre domaines différents sans restriction.
             ⚠️ À utiliser avec précaution en production ! Sinon on risque des failles de sécurité (ex : des sites tiers accèdent à ton API).
           */
           // .cors({})
            .authenticationProvider(customProvider)
            /*Définition des règles d'autorisation pour les différentes requêtes HTTP.
             */.authorizeHttpRequests(
                { auth ->

                    auth.requestMatchers(
                        "/api/v1/settings/get-user-settings",
                        "/swagger-ui/**",
                        "/api/v1/websocket/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/v2/api-docs/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/api/v1/auth/authenticate",
                        "/api/v2/auth/login",
                        "/api/v1/auth/login",
                        "/api/v1/auth/register",
                        "/api/v2/auth/register",
                        "/api/v1/auth/verify-code",
                        "/api/v1/files/**",
                        "/error",
                        "/api/v2/auth/verify-identity",
                        "/api/v2/auth/refresh-token",
                        "/api/v2/offre-immobiliere/**",
                        "/ws/**"
                    ).permitAll().anyRequest().authenticated()
                }
            )
            .addFilterBefore(
                JwtFilter(userTokenService, userDetailsService, jwtTokenUtil),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .sessionManagement({ session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) })
            .build()
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("*") // ou par exemple : "http://localhost:3000"
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")  }
        }
    }
    /*  @Bean
      public AuthenticationManager authenticationManager() {
          DaoAuthenticationProvider provider = new DaoAuthenticationProvider(passwordEncoder);
          provider.setUserDetailsService(userDetailsService);
    d      return new ProviderManager(provider);
      }*/
    @Bean
    public fun authenticationManager(): AuthenticationManager {
        return ProviderManager(
            listOf(
                customProvider, // ✅ Ton provider personnalisé
                DaoAuthenticationProvider(passwordEncoder).apply {
                    setUserDetailsService(userDetailsService)
                }
            )
        );
    }
}
