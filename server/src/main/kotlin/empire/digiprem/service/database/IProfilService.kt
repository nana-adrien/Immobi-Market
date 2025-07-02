package empire.digiprem.service.database

import empire.digiprem.models.database.UserProfile
import empire.digiprem.models.database.User

interface IProfilService {
    fun createUserProfile(profile: UserProfile)
    fun UpdateUserProfile(profile:UserProfile)
    fun changeUserProfileImage(user: User, profileUrl:String)
}