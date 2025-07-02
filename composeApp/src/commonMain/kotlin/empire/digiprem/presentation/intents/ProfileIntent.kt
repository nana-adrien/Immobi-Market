package empire.digiprem.presentation.intents

import empire.digiprem.enums.Gender
import empire.digiprem.model.AppFile

sealed class ProfileIntent {
 data class OnProfilePictureChange(val newPicture: AppFile) : ProfileIntent()
 data class OnLastNameChange(val lastName: String) : ProfileIntent()
 data class OnFirstNameChange(val firstName: String) : ProfileIntent()
 data class OnGenderSelect(val gender: Gender) : ProfileIntent()
 data class OnPhoneChange(val phone: String) : ProfileIntent()
 data class OnTwoFactorAuthToggle(val enabled: Boolean) : ProfileIntent()
 data class OnTwoFactorPrivacyToggle(val enabled: Boolean) : ProfileIntent()
 object OnClickPolicy : ProfileIntent()
 object OnSubmit : ProfileIntent()
 }