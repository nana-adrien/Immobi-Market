package empire.digiprem.presentation.models

import empire.digiprem.data.local.DataBaseTemp
import empire.digiprem.enums.Gender
import empire.digiprem.presentation.components.AppTextFieldState
import empire.digiprem.presentation.views.AppFileUploading

data class ProfilModel(
    val lastNameTextField: AppTextFieldState = AppTextFieldState(),
    val firstNameTextField: AppTextFieldState = AppTextFieldState(),
    val gender: Gender = Gender.MALE,
    val phoneTextField: AppTextFieldState = AppTextFieldState(),
    val bornDate: AppTextFieldState = AppTextFieldState(),
    val profileUrl: AppFileUploading = AppFileUploading(),
    val twoFactorAuth: Boolean = false,
    val enabledPrivacyPolitic: Boolean = false,
    val enabledSendButton: Boolean = false,
    val isFormValid: Boolean = false
)
