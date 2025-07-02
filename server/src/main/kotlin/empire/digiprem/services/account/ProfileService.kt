package empire.digiprem.services

import empire.digiprem.dto.profile.GetProfileResponse
import empire.digiprem.dto.profile.UpdateProfileRequest
import empire.digiprem.dto.profile.UpdateProfileResponse
import empire.digiprem.exception.ValidateTextFiledException
import empire.digiprem.models.database.User
import empire.digiprem.models.database.UserProfile
import empire.digiprem.repositories.database.IUserProfileRepository
import empire.digiprem.repositories.database.UsersRepository
import empire.digiprem.services.auth.UserAccountService
import empire.digiprem.utils.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDateTime
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class ProfileService(
    private val userAccountService: UserAccountService,
    private val userRepository: UsersRepository,
    private val userProfileRepository: IUserProfileRepository
) {
    fun getProfile(user: User): GetProfileResponse {
        val profile=userProfileRepository.findByUser(user).get()
        return GetProfileResponse(
            name = profile.nom,
            firstName = profile.prenom,
            profileUrl = profile.urlProfil,
            email = user.email,
            role = user.role,
            isEmailVerified =true,
            isPhoneVerified = profile.telephoneVerifie,
            isComplete = profile.profilComplet,
            isOnline =true,
            createdAt = profile.dateCreation.toKotlinLocalDateTime()
        )
    }
    fun getProfileBYUserId(userIdentity: String): GetProfileResponse {
        val user = userAccountService.loadUserByIdentity(userIdentity, true)
            ?: throw ValidateTextFiledException(field = "identity", "User not found")
        return getProfile(user)
    }

   /* fun updateProfile(identity: String, profile: UpdateProfileRequest): UpdateProfileResponse {
        try {
            val fields = mutableListOf<String>()
            var user = userAccountService.loadUserByIdentity(identity, true)
                ?: throw ValidateTextFiledException(field = "identity", "User not found")

            profile.profileUrl?.let { url -> user = user.copy(profileUrl = url); fields.add("profileUrl") }
            profile.name?.let { name -> user = user.copy(name = name); fields.add("name") }
            profile.firstName?.let { firstName -> user = user.copy(firstName = firstName); fields.add("firstName") }
            profile.bornAt?.let { date->user=user.copy(bornAt = date.toJavaLocalDate() ) ;  fields.add("bornAt")}
            profile.enableTwoFactorAuth?.let { enableTwoFactorAuth ->
                user = user.copy(enableTwoFactorAuth = enableTwoFactorAuth); fields.add("enableTwoFactorAuth")
            }
            profile.userPhoneNumber?.let { phoneNumber ->
                user =
                    user.copy(userPhoneNumber = userAccountService.createPhoneNumber(phoneNumber.getUserPhoneNumber())); fields.add(
                "userPhoneNumber"
            )
            }
            userRepository.save(user)
            return UpdateProfileResponse(
                fields = fields,
                message = "${fields.size} valeurs mises à jour avec susses"
            )
        } catch (
            e: Exception
        ) {
            throw RuntimeException(e.message)
        }

    }*/

}
