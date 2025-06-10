package empire.digiprem.controller

import com.google.firebase.messaging.FirebaseMessagingException
import empire.digiprem.dto.NotificationRequest
import empire.digiprem.dto.NotificationResponse
import empire.digiprem.extension.Api200
import empire.digiprem.extension.successResponse
import empire.digiprem.model.ApiResponse2
import empire.digiprem.services.FCMService
import empire.digiprem.services.FCMService2
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ExecutionException

@Tag(name = "notification")
@RestController
@RequestMapping("api/v1/notification")
class NotificationController(private val fcmService: FCMService, private val fcmService2: FCMService2) {
    /*
       * Nous avons exposé un point de terminaison /notification qui acceptera un objet NotificationRequest et appellera la méthode sendMessageToToken() de notre classe de service pour envoyer la notification push.
        * */
    @PostMapping("/send")
    @Throws(ExecutionException::class, InterruptedException::class)
    fun sendNotification(@RequestBody request: NotificationRequest): ResponseEntity<ApiResponse2<NotificationResponse>> {
        fcmService.sendMessageToken(request)
        return ApiResponse2.successResponse(NotificationResponse(HttpStatus.OK.value(), "Notification has been sent."))
    }

    @Api200
    @PostMapping("/send2")
    @Throws(
        ExecutionException::class,
        InterruptedException::class,
        FirebaseMessagingException::class
    )
    fun sendNotification2(@RequestBody request: NotificationRequest): NotificationResponse {
        fcmService2.sendNotification(request.token, request.titre, request.body)
        return NotificationResponse(HttpStatus.OK.value(), "Notification has been sent.")
    }
}