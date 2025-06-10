package empire.digiprem.dto


data class NotificationRequest(
    val titre: String,
    val body: String,
    val topic: String,
    val token: String
)