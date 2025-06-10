package empire.digiprem.presentation.models

import empire.digiprem.presentation.components.AppTextFieldState

data class VerifyIdentityModel(
    val isLoading: Boolean = false,
    val enableSendButton: Boolean = false,
    val pinCode:AppTextFieldState = AppTextFieldState()
)