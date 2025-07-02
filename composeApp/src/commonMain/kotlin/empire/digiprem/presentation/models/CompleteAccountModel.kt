package empire.digiprem.presentation.models

import androidx.compose.ui.text.input.TextFieldValue
import empire.digiprem.enums.Gender
import empire.digiprem.model.AppFile
import empire.digiprem.presentation.components.AppTextFieldState

data class CompleteAccountModel(
    val lastNameTextField: AppTextFieldState = AppTextFieldState(),
    val firstNameTextField: AppTextFieldState =AppTextFieldState(),
    val gender: Gender = Gender.MALE,
    val phoneTextField: AppTextFieldState =AppTextFieldState(),
    val bornDate:AppTextFieldState=AppTextFieldState(),
    val twoFactorAuth: Boolean = false,
    val enabledPrivacyPolitic: Boolean = false,
    val enabledSendButton: Boolean = false,
    val isFormValid: Boolean = false
)