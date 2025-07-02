package empire.digiprem.presentation.viewmodels

import androidx.navigation.NavController
import empire.digiprem.data.config.DataSourceEventHandlerDecorator
import empire.digiprem.data.local.DataBaseTemp
import empire.digiprem.data.remote.service.OAuthEndPointEndPointService
import empire.digiprem.domain.repository.TokenStorage
import empire.digiprem.dto.auth.login.LoginResponseDTO
import empire.digiprem.dto.auth.login.LoginResponseDTO2
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.enums.TokenEnum
import empire.digiprem.enums.VerificationOperation
import empire.digiprem.model.ApiResponse2
import empire.digiprem.model.TokensResult
import empire.digiprem.navigation.ViewHome
import empire.digiprem.navigation.ViewSuccessfulAuth
import empire.digiprem.navigation.ViewVerifyIdentity
import empire.digiprem.presentation.intents.LoginIntent
import empire.digiprem.presentation.intents.RegisterIntent
import empire.digiprem.presentation.models.LoginModel
import empire.digiprem.presentation.viewmodels.RegisterViewModel.Companion
import empire.digiprem.presentation.viewmodels.RegisterViewModel.Companion.CONFIRM_PASSWORD_TEXTFIELD
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.viewmodels.componenet.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel(val navController: NavController) :
    AbstractViewModel<LoginModel, LoginIntent>(defaultState = LoginModel()), KoinComponent {
    // TODO: ViewModel logic


    private val tokenStorage: TokenStorage by inject()
    companion object {
        const val EMAIL_TEXTFIELD = "email"
        const val PASSWORD_TEXTFIELD = "password"
    }

    private val oauthEndPointService = OAuthEndPointEndPointService()

    override fun onIntentHandler(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.OnSendForm -> {
                defaultScope.launch {
                    onSendForm(
                        identity = _mutableState.value.emailTextField.value,
                        password = _mutableState.value.passwordTextField.value,
                    )
                }
            }

            is LoginIntent.onChangeTextField -> {
                changeTextField(intent.key, intent.value)
            }
        }
    }


    private fun changeTextField(key: String, value: String) {
        val state = _mutableState.value
        when (key) {
            EMAIL_TEXTFIELD -> {
                _mutableState.update {
                    it.copy(
                        emailTextField = state.emailTextField.copy(value = value),
                    )
                }
            }

            PASSWORD_TEXTFIELD -> {
                _mutableState.update {
                    it.copy(
                        passwordTextField = state.passwordTextField.copy(value = value)
                    )
                }
            }
        }
        enableSendButton()
    }

    private fun enableSendButton() {
        val state = _mutableState.value
        _mutableState.update {
            it.copy(
                enableSendButton = state.emailTextField.value.isNotEmpty()
                        && state.passwordTextField.value.isNotEmpty()
            )
        }
    }

    suspend fun onSendForm(
        identity: String,
        password: String
    ) {
        collectDataSourceEvent(
            oauthEndPointService.login(
                password = password,
                email = identity
            ),
            object : DataSourceEventHandlerDecorator<LoginResponseDTO2>() {
                override suspend fun onLoading() {
                    super.onLoading()
                    _mutableState.update {
                        it.copy(
                            enableSendButton = false
                        )
                    }
                }

                override suspend fun onSuccessConnexion(key: Any?, data: LoginResponseDTO2) {
                    super.onSuccessConnexion(key, data)
                    _mutableState.update {
                        it.copy(
                            enableSendButton = true
                        )
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        if (data.enabledTwoFactorAuthentication) {
                            navController.navigate(ViewVerifyIdentity(email = _mutableState.value.emailTextField.value, operationType = VerificationOperation.AUTHENTICATION.name))
                        }else   {
                            data.tokens?.let { saveToken(it) }
                            navController.navigate(ViewSuccessfulAuth())
                        }
                    }
                }

                override suspend fun onErrorConnexion(code: Int, messages: List<ApiResponse2.ErrorMessage>) {
                    super.onErrorConnexion(code, messages)

                    _mutableState.update {
                        it.copy(
                            enableSendButton = true
                        )
                    }
                    validateTextFild(messages)
                }

                override suspend fun onFailConnexion(message: String) {
                    super.onFailConnexion(message)
                    _mutableState.update {
                        it.copy(
                            enableSendButton = true
                        )
                    }
                }

                override suspend fun onFailProcess() {
                    super.onFailProcess()
                    _mutableState.update {
                        it.copy(
                            enableSendButton = true
                        )
                    }
                }
            }
        )
    }

    override fun onRefreshPage() {

    }

    fun validateTextFild(errorMessage: List<ApiResponse2.ErrorMessage>) {
        val validate: (textFieldName: String) -> String = { textFieldName ->
            errorMessage.first { it.field == textFieldName }.message
        }
        _mutableState.update {
            it.copy(
                emailTextField = it.emailTextField.copy(
                    isError = validate(EMAIL_TEXTFIELD).isNotEmpty(),
                    errorMessage = validate(EMAIL_TEXTFIELD)
                ),
                passwordTextField = it.passwordTextField.copy(
                    isError = validate(PASSWORD_TEXTFIELD).isNotEmpty(),
                    errorMessage = validate(PASSWORD_TEXTFIELD)
                ),
            )
        }
    }
    suspend fun saveToken(token: TokensResult) {
        tokenStorage.saveToken(TokenEnum.ACCESS_TOKEN,token.accessToken)
        tokenStorage.saveToken(TokenEnum.REFRESH_TOKEN,token.refreshToken)
    }
}