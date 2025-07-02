package empire.digiprem.services.auth

import empire.digiprem.model.TokensResult
import empire.digiprem.models.database.User
import empire.digiprem.models.database.UserToken
import empire.digiprem.repositories.database.IUserTokenRepository
import empire.digiprem.service.IUserAuthenticateService
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Service
class UserTokenService(
    val userTokenRepository: IUserTokenRepository,
    val userAccountService: IUserAuthenticateService
) {@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
    fun saveToken(username: String, token: TokensResult): UserToken {
        val user = userAccountService.loadUserByIdentity(username)
        val userToken = UserToken(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken,
            user = user as User
        )
        return userTokenRepository.save(userToken)
    }
    fun saveToken(userToken: UserToken): UserToken {
        return userTokenRepository.save(userToken)
    }
    @OptIn(ExperimentalUuidApi::class)
    fun getUserTokenById(username: String, tokenId: UUID): UserToken? {
        val user = userAccountService.loadUserByIdentity(username)
        val userToken = userTokenRepository.findByUserAndId(user as User, tokenId)
        return userToken.getOrNull()
    }
  /*  fun getUserToken(token: String): UserToken? {
        val user = userAccountService.loadUserByUsername(username)
        val userToken = userTokenRepository.findByUserAndId(user as User, tokenId)
        return userToken.getOrNull()
    }*/
  @OptIn(ExperimentalUuidApi::class)
  fun getUserTokenByUserAndId(user: User, tokenId:  UUID): UserToken? {
        val userToken = userTokenRepository.findByUserAndId(user, tokenId)
        return userToken.getOrNull()
    }
    fun getAllUserToken(username: String): List<UserToken> {
        val user = userAccountService.loadUserByIdentity(username)
        val userToken = userTokenRepository.findByUser(user as User)
        return userToken.getOrElse { emptyList() }
    }
    fun revoke(username: String) {
        getAllUserToken(username).let { tokens ->
            if (tokens.isNotEmpty()){
                userTokenRepository.deleteAll(tokens)
            }
        }
    }
    fun revokeAllForUser(username: String) {
        getAllUserToken(username).let { tokens ->
            if (tokens.isNotEmpty()){
                userTokenRepository.deleteAll(tokens)
            }
        }
    }
}