package empire.digiprem.models.database

import empire.digiprem.enums.NotificationCanal
import empire.digiprem.utils.convertor.NotificationCanalConverter
import jakarta.persistence.*
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
data class UserSettings @OptIn(ExperimentalUuidApi::class) constructor(
    @OneToOne
    @MapsId("id")
    val user: User,
    @Id val id: UUID = UUID.randomUUID(),
    val enabledTwoFactorAuthentication: Boolean = true,
    val preferLanguageTag: String = "en",
    val enableLightMode: Boolean = true,
    @Convert(converter = NotificationCanalConverter::class)
    val notificationCanal: NotificationCanal = NotificationCanal.EMAIL,
    val userCanChangeNotificationCanal: Boolean = false,
)