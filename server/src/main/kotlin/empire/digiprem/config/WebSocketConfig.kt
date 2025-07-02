package empire.digiprem.config;


import empire.digiprem.interceptor.WebSocketAuthenticationInterceptor;
import empire.digiprem.service.IUserDetailService2;
import empire.digiprem.utils.JwtTokenUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig(
    val jwtTokenUtil :JwtTokenUtil,
    val userDetailService: IUserDetailService2
)  : WebSocketMessageBrokerConfigurer {

    override fun registerStompEndpoints( registry:StompEndpointRegistry) {
        /* Déclare l'endpoint WebSocket à l'URL "/ws"
         Avec SockJS pour la compatibilité avec les anciens navigateurs*/
        registry.addEndpoint("/ws").withSockJS();
        registry.addEndpoint("/ws");
    }
    override fun  configureMessageBroker( registry:MessageBrokerRegistry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
    override fun configureClientInboundChannel( registration:ChannelRegistration) {
     registration.interceptors( WebSocketAuthenticationInterceptor(jwtTokenUtil,userDetailService));
    }
}
