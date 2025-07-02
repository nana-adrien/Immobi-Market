package empire.digiprem.config;


import empire.digiprem.handler.ServerWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class ServerWebSocketConfig : WebSocketConfigurer {
    override fun registerWebSocketHandlers( registry:WebSocketHandlerRegistry) {
        registry.addHandler(webSocketHandler(),"/websocket").withSockJS();
        registry.addHandler(webSocketHandler(),"/websocket");
    }
    @Bean
    public fun webSocketHandler():WebSocketHandler {
        return  ServerWebSocketHandler();
    }
}
