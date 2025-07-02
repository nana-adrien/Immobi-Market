package empire.digiprem.presentation.intents

import empire.digiprem.enums.Gender
import empire.digiprem.model.AppFile

sealed class CompleteAccountIntent {
    data class OnLastNameChange(val lastName: String) : CompleteAccountIntent()
    data class OnFirstNameChange(val firstName: String) : CompleteAccountIntent()
    data class OnGenderSelect(val gender: Gender) : CompleteAccountIntent()
    data class OnPhoneChange(val phone: String) : CompleteAccountIntent()
    data class OnTwoFactorAuthToggle(val enabled: Boolean) : CompleteAccountIntent()
    data class OnTwoFactorPrivacyToggle(val enabled: Boolean) : CompleteAccountIntent()
     object OnClickPolicy : CompleteAccountIntent()
    object OnSubmit : CompleteAccountIntent()


}