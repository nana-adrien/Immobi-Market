package empire.digiprem.presentation.viewmodels

import androidx.navigation.NavController
import empire.digiprem.data.config.DataSourceEventHandlerDecorator
import empire.digiprem.data.remote.service.OAuthEndPointEndPointService
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.model.ApiResponse2
import empire.digiprem.navigation.ViewVerifyIdentity
import empire.digiprem.presentation.intents.RegisterIntent
import empire.digiprem.presentation.models.RegisterModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(val navController: NavController) : AbstractViewModel<RegisterModel, RegisterIntent>(defaultState = RegisterModel()) {


    companion object {
        const val EMAIL_TEXTFIELD = "email"
        const val PASSWORD_TEXTFIELD = "password"
        const val CONFIRM_PASSWORD_TEXTFIELD = "confirmPassword"
    }

    private val oauthEndPointService=OAuthEndPointEndPointService()

    // TODO: ViewModel logic
    override fun onIntentHandler(intent: RegisterIntent) {
        when (intent) {
            RegisterIntent.OnSendForm -> {
                defaultScope.launch {
                    onSendForm(
                        identity = _mutableState.value.emailTextField.value ,
                        isEmail = true,
                        password =  _mutableState.value.passwordTextField.value ,
                        confirmPassword =  _mutableState.value.confirmPasswordTextField.value ,
                    )
                }

            }
            is RegisterIntent.onChangeTextField -> {
                changeTextField(intent.key, intent.value)
                println("key=${intent.key}, value=${intent.value}")
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
                        passwordTextField = state.passwordTextField.copy(value = value),
                        confirmPasswordTextField = state.confirmPasswordTextField.copy(
                            isError =  state.confirmPasswordTextField.value!=value && state.confirmPasswordTextField.value.isNotEmpty()
                        ),

                    )
                }
            }
            CONFIRM_PASSWORD_TEXTFIELD -> {

                _mutableState.update {
                    it.copy(
                        confirmPasswordTextField = state.confirmPasswordTextField.copy(
                            value = value,
                            isError =  state.passwordTextField.value!=value

                        ),
                    )
                }
            }
        }
        enableSendButton()
        println("state=${state}")
    }
    private fun enableSendButton(){
        val state=_mutableState.value
        _mutableState.update {
            it.copy(
                enableSendButton = state.emailTextField.value.isNotEmpty()
                        && state.passwordTextField.value.isNotEmpty()
                        && state.confirmPasswordTextField.value.isNotEmpty()
                        && !state.confirmPasswordTextField.isError

            )
        }
    }

    suspend fun onSendForm(identity: String,
                           password: String,
                           confirmPassword: String,
                           isEmail: Boolean) {
        collectDataSourceEvent(
            oauthEndPointService.register(
                isEmail =isEmail ,
                confirmPassword = confirmPassword,
                password = password,
                identity = identity
            ),
            object:DataSourceEventHandlerDecorator<RegisterResponseDTO>(){
                override suspend fun onLoading() {
                    super.onLoading()
                    _mutableState.update {
                        it.copy(
                            enableSendButton = false
                        )
                    }
                }
                override suspend fun onSuccessConnexion(key: Any?, data: RegisterResponseDTO) {
                    super.onSuccessConnexion(key, data)

                    _mutableState.update {
                        it.copy(
                            enableSendButton = true
                        )
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        navController.navigate(ViewVerifyIdentity(email = data.email))
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

    fun validateTextFild(errorMessage: List<ApiResponse2.ErrorMessage>){
        val validate:(textFieldName:String)-> String={ textFieldName ->
            errorMessage.first { it.field == textFieldName }.message
        }
        _mutableState.update {
            it.copy(
                emailTextField = it.emailTextField.copy(
                    isError =validate(EMAIL_TEXTFIELD).isNotEmpty(),
                    errorMessage =  validate(EMAIL_TEXTFIELD)
                ),
                passwordTextField = it.passwordTextField.copy(
                    isError =validate(PASSWORD_TEXTFIELD).isNotEmpty(),
                    errorMessage = validate(PASSWORD_TEXTFIELD)
                ),
                confirmPasswordTextField = it.confirmPasswordTextField.copy(
                    isError =validate(CONFIRM_PASSWORD_TEXTFIELD).isNotEmpty(),
                    errorMessage =validate(CONFIRM_PASSWORD_TEXTFIELD),
                )
            )
        }
    }
}