package empire.digiprem.dto.auth

import empire.digiprem.dto.UserRoleDTO

data class RegisterRespDto(val id: Long, val username: String, val email: String, val roles: Collection<UserRoleDTO>)