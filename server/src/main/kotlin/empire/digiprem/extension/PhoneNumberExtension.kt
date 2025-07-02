package empire.digiprem.extension

import empire.digiprem.model.PhoneNumber
import empire.digiprem.models.database.UserPhoneNumber
import empire.digiprem.models.database.UserProfile
@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
fun PhoneNumber.getUserPhoneNumber(userProfile: UserProfile): UserPhoneNumber {
    return UserPhoneNumber(
        id =  userProfile.user_id,
        userProfile = userProfile,
        phoneNumber = this.phoneNumber,
        countryCode = this.countryCode?:"+237"
    )
}
