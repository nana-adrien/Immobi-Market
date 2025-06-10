package empire.digiprem.controller

import empire.digiprem.dto.SendMessageRequest
import empire.digiprem.extension.error
import empire.digiprem.extension.success
import empire.digiprem.model.ApiResponse2
import empire.digiprem.model.ChatMessage
import empire.digiprem.model.MessageType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@Tag(name = "chat", description = "wss://(Protocole WebSocket required) ")
@Controller
@RestController
@RequestMapping("/api/v1/websocket/")
class ChatController(messagingTemplate: SimpMessagingTemplate) {
    private val messagingTemplate: SimpMessagingTemplate = messagingTemplate

    @Operation(description = "/app/sendMessage")
    fun sendMessage(@RequestBody sendMessageRequest: SendMessageRequest?): ApiResponse2<ChatMessage?> {
        return ApiResponse2.error(
            listOf(
                ApiResponse2.ErrorMessage(
                    " ERROR PROTOCOL HTTP",
                    "❌ Mauvais protocole : cette méthode doit être appelée via WebSocket à "
                )
            )
        )
    }

    @PostMapping("/app/sendMessage")
    @MessageMapping("/sendMessage")
    fun sendMessage(
        @RequestBody @Payload message: SendMessageRequest,
        principal: Principal,
        request: HttpServletRequest
    ): ApiResponse2<ChatMessage> {
        val scheme = request.scheme // http ou https
        if ("http".equals(scheme, ignoreCase = true) || "https".equals(scheme, ignoreCase = true)) {
            return ApiResponse2.error<ChatMessage>(
                listOf(
                    ApiResponse2.ErrorMessage(
                        " ERROR PROTOCOL HTTP",
                        "❌ Mauvais protocole : cette méthode doit être appelée via WebSocket à "
                    )
                )
            )
        }
        val chatMessage = ChatMessage(
            message.type, message.content, principal.name
        )
        println("message = " + chatMessage.type)
        messagingTemplate.convertAndSend("/topic/public", chatMessage)
        return ApiResponse2.success(chatMessage)
    }

    @GetMapping("/app/receiveMessage")
    @MessageMapping("/app/receiveMessage")
    fun getMessage(request: HttpServletRequest): ApiResponse2<ChatMessage> {
        val scheme = request.scheme // http ou https
        if ("http".equals(scheme, ignoreCase = true) || "https".equals(scheme, ignoreCase = true)) {
            throw RuntimeException("❌ Mauvais protocole : cette méthode doit être appelée via WebSocket à ")
            //return  ApiResponse2.error(List.of(new ApiResponse2.ErrorMessage(" ERROR PROTOCOL HTTP",             "❌ Mauvais protocole : cette méthode doit être appelée via WebSocket à ")) );
        }
        val chatMessage = ChatMessage(
            MessageType.LEAVE, "message.content()", "user"
        )
        return ApiResponse2.success(chatMessage)
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/public")
    fun addUser(@Payload message: ChatMessage, headerAccessor: SimpMessageHeaderAccessor): ChatMessage {
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", message.sender)
        return message
    }
}