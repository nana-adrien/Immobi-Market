package empire.digiprem.presentation.models

import empire.digiprem.presentation.components.AppTextFieldState

data class RegisterModel(
    val isLoading: Boolean = false,
    val isError:Boolean=false,
    val enableSendButton:Boolean=false,
    val emailTextField: AppTextFieldState =AppTextFieldState(),
    val passwordTextField: AppTextFieldState =AppTextFieldState(),
    val confirmPasswordTextField: AppTextFieldState =AppTextFieldState(),
)