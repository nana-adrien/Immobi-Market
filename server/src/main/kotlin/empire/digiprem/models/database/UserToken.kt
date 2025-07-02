package empire.digiprem.models.database

import jakarta.persistence.*
import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
data class UserToken @OptIn(ExperimentalUuidApi::class) constructor(
    @Id val id: UUID = UUID.randomUUID(),
    @Column(columnDefinition = "text") val accessToken:String="",
    @Column(columnDefinition = "text") val refreshToken:String="",
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User?=null,
   //  val user_id:Uuid= Uuid.random(),

    )