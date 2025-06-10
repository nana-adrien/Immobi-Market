package empire.digiprem

import empire.digiprem.service.IUserConversationService
import empire.digiprem.services.ConversationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChatModuleConfiguration {
    @Bean
    fun userConversationService(): IUserConversationService {
        return ConversationService()
    }
}