package empire.digiprem.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import empire.digiprem.data.config.DataSourceEventHandlerDecorator
import empire.digiprem.data.remote.service.AccountService
import empire.digiprem.dto.account.CreateAccountRequestDTO
import empire.digiprem.dto.account.CreateAccountResponse
import empire.digiprem.dto.auth.register.RegisterResponseDTO
import empire.digiprem.enums.Gender
import empire.digiprem.enums.VerificationOperation
import empire.digiprem.model.ApiResponse2
import empire.digiprem.model.PhoneNumber
import empire.digiprem.navigation.ViewSuccessfulAuth
import empire.digiprem.navigation.ViewVerifyIdentity
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.intents.CompleteAccountIntent
import empire.digiprem.presentation.models.CompleteAccountModel
import empire.digiprem.presentation.viewmodels.RegisterViewModel.Companion.CONFIRM_PASSWORD_TEXTFIELD
import empire.digiprem.presentation.viewmodels.RegisterViewModel.Companion.EMAIL_TEXTFIELD
import empire.digiprem.presentation.viewmodels.RegisterViewModel.Companion.PASSWORD_TEXTFIELD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CompleteAccountViewModel(val navController: NavController) :
    AbstractViewModel<CompleteAccountModel, CompleteAccountIntent>(defaultState = CompleteAccountModel()) {
    // TODO: ViewModel logic

    private val accountSevice = AccountService()

    companion object {
        const val FIRST_NAME_TEXTFIELD = "firstName"
        const val NAME_TEXTFIELD = "name"
        const val PHON_NUMBER_TEXTFIELD = "userPhoneNumber"
    }

    override fun onIntentHandler(intent: CompleteAccountIntent) {

        viewModelScope.launch {
            when (intent) {
                CompleteAccountIntent.OnClickPolicy -> {

                }

                is CompleteAccountIntent.OnFirstNameChange -> {
                    changeTextField(
                        key = FIRST_NAME_TEXTFIELD,
                        value = intent.firstName
                    )
                }

                is CompleteAccountIntent.OnGenderSelect ->{
                    _mutableState.update {
                        it.copy(gender = intent.gender)
                    }
                    enabledSendButton()
                }
                is CompleteAccountIntent.OnLastNameChange ->{
                    changeTextField(
                        key = NAME_TEXTFIELD,
                        value = intent.lastName
                    )
                }
                is CompleteAccountIntent.OnPhoneChange -> {
                    changeTextField(
                        key = PHON_NUMBER_TEXTFIELD,
                        value = intent.phone
                    )
                }
                CompleteAccountIntent.OnSubmit -> {
                    onSendForm()
                }
                is CompleteAccountIntent.OnTwoFactorAuthToggle ->{
                    _mutableState.update {
                        it.copy(
                            twoFactorAuth = intent.enabled,
                        )
                    }

                    enabledSendButton()
                }
                is CompleteAccountIntent.OnTwoFactorPrivacyToggle ->{
                    _mutableState.update {
                        it.copy(
                            enabledPrivacyPolitic = intent.enabled,
                        )
                    }
                    enabledSendButton()
                }
            }
        }
    }

    fun enabledSendButton() {
        val state = _mutableState.value
        _mutableState.update {
            it.copy(
                enabledSendButton = state.lastNameTextField.value.isNotEmpty() && state.enabledPrivacyPolitic //&& state.bornDate.value.isNotEmpty()

            )
        }
    }


    private fun changeTextField(key: String, value: String) {
        val state = _mutableState.value
        when (key) {
            FIRST_NAME_TEXTFIELD -> {
                _mutableState.update {
                    it.copy(
                        firstNameTextField = state.firstNameTextField.copy(value = value),
                    )
                }
            }

            NAME_TEXTFIELD -> {

                _mutableState.update {
                    it.copy(
                        lastNameTextField = state.lastNameTextField.copy(value = value),
                    )

                }
            }

            PHON_NUMBER_TEXTFIELD -> {

                _mutableState.update {
                    it.copy(
                        phoneTextField = state.phoneTextField.copy(
                            value = value,
                        ),
                    )
                }
            }
        }
        enabledSendButton()
    }


    suspend fun onSendForm() {

        collectDataSourceEvent(
            accountSevice.createAccount(
                lastName =_mutableState.value.lastNameTextField.value,
                firstName = _mutableState.value.firstNameTextField.value,
                gender = _mutableState.value.gender,
                phoneNumber =_mutableState.value.phoneTextField.value,
                enabledTowFactorAuth = _mutableState.value.twoFactorAuth
            ),
            object : DataSourceEventHandlerDecorator<CreateAccountResponse>() {
                override suspend fun onLoading() {
                    super.onLoading()
                    _mutableState.update {
                        it.copy(enabledSendButton = false
                        )
                    }
                }

                override suspend fun onSuccessConnexion(key: Any?, data: CreateAccountResponse) {
                    super.onSuccessConnexion(key, data)

                    _mutableState.update {
                        it.copy(
                            enabledSendButton = true
                        )
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        navController.navigate(
                            ViewSuccessfulAuth()
                        )
                    }
                }

                override suspend fun onErrorConnexion(code: Int, messages: List<ApiResponse2.ErrorMessage>) {
                    super.onErrorConnexion(code, messages)

                    _mutableState.update {
                        it.copy(
                            enabledSendButton = true
                        )
                    }
                    validateTextFild(messages)
                }

                override suspend fun onFailConnexion(message: String) {
                    super.onFailConnexion(message)
                    _mutableState.update {
                        it.copy(
                            enabledSendButton = true
                        )
                    }
                }

                override suspend fun onFailProcess() {
                    super.onFailProcess()
                    _mutableState.update {
                        it.copy(
                            enabledSendButton = true
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
                lastNameTextField = it.lastNameTextField.copy(
                    isError = validate(NAME_TEXTFIELD).isNotEmpty(),
                    errorMessage = validate(NAME_TEXTFIELD)
                ),
                firstNameTextField = it.firstNameTextField.copy(
                    isError = validate(FIRST_NAME_TEXTFIELD).isNotEmpty(),
                    errorMessage = validate(FIRST_NAME_TEXTFIELD)
                ),
                phoneTextField = it.phoneTextField.copy(
                    isError = validate(PHON_NUMBER_TEXTFIELD).isNotEmpty(),
                    errorMessage = validate(PHON_NUMBER_TEXTFIELD),
                )
            )
        }
    }
}