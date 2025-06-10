package empire.digiprem.dto.auth

import empire.digiprem.models.UserRole

@JvmRecord
data class RegisterRespDto(val id: Long, val username: String, val email: String, val roles: Collection<UserRole>)