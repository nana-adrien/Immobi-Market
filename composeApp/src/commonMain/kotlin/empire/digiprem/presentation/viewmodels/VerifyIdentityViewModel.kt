package empire.digiprem.presentation.viewmodels

import androidx.navigation.NavController
import empire.digiprem.app.config.Log
import empire.digiprem.core.helpers.getAccessTokenClaims
import empire.digiprem.data.config.DataSourceEventHandlerDecorator
import empire.digiprem.data.remote.service.OAuthEndPointEndPointService
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityResponseDTO
import empire.digiprem.model.ApiResponse2
import empire.digiprem.model.TokenHelper
import empire.digiprem.model.TokensResult
import empire.digiprem.navigation.ViewCompleteAccount
import empire.digiprem.presentation.intents.VerifyIdentityIntent
import empire.digiprem.presentation.models.VerifyIdentityModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VerifyIdentityViewModel(val navController: NavController) :
    AbstractViewModel<VerifyIdentityModel, VerifyIdentityIntent>(defaultState = VerifyIdentityModel()) {
    // TODO: ViewModel logic
    private val oauthEndPointService = OAuthEndPointEndPointService()

    companion object {
        const val PINCODE_TEXTFIELD = "pinCode"
    }

    override fun onIntentHandler(intent: VerifyIdentityIntent) {
        when (intent) {
            is VerifyIdentityIntent.OnPinCodeChange -> {
                changeTextField(intent.pincode.joinToString(""))
            }

            is VerifyIdentityIntent.OnSendForm -> {
                defaultScope.launch {
                    onVerifyIdentity(
                        identity = intent.email,
                        pinCode = _mutableState.value.pinCode.value
                    )
                }
            }
        }
    }

    private fun changeTextField(value: String) {
        val state = _mutableState.value
        _mutableState.update {
            it.copy(
                pinCode = state.pinCode.copy(value = value),
                enableSendButton = state.pinCode.value.isNotEmpty()
            )
        }
        enableSendButton()
        println("state=${state}")
    }

    private fun enableSendButton() {
        val state = _mutableState.value
        _mutableState.update {
            it.copy(
                enableSendButton = state.pinCode.value.length==6
            )
        }
    }

    suspend fun onVerifyIdentity(
        identity: String,
        pinCode: String
    ) {
        collectDataSourceEvent(
            oauthEndPointService.verifyIdentity(identity, pinCode),
            handler = object : DataSourceEventHandlerDecorator<VerifyIdentityResponseDTO>() {
                override suspend fun onSuccessConnexion(key: Any?, data: VerifyIdentityResponseDTO) {
                    super.onSuccessConnexion(key, data)
                    _mutableState.update {
                        it.copy(
                            enableSendButton = true
                        )
                    }
                    Log.i("AuthRespDto","data=$data")
                    val token=data.accessToken
                    TokensResult.getAccessTokenClaims(token,TokenHelper.IDENTITY)
                    mainScope.launch {
                        navController.navigate(ViewCompleteAccount(email = TokensResult.getAccessTokenClaims(token,TokenHelper.IDENTITY)))
                    }
                }
                override suspend fun onLoading() {
                    super.onLoading()
                    _mutableState.update {
                        it.copy(
                            enableSendButton = false
                        )
                    }
                }
                override suspend fun onFailConnexion(message: String) {
                    super.onFailConnexion(message)
                    _mutableState.update {
                        it.copy(
                            enableSendButton = true
                        )
                    }
                }
                override suspend fun onErrorConnexion(code: Int, messages: List<ApiResponse2.ErrorMessage>) {
                    super.onErrorConnexion(code, messages)
                    _mutableState.update {
                        it.copy(
                            enableSendButton = true
                        )
                    }
                    validatePincode(messages)
                }
            }
        )
    }

    suspend fun validatePincode(errorMessage: List<ApiResponse2.ErrorMessage>) {
        _mutableState.update {
            it.copy(
                pinCode = _mutableState.value.pinCode.copy(
                    isError = errorMessage.any { it.field == PINCODE_TEXTFIELD },
                    errorMessage = errorMessage.filter { it.field == PINCODE_TEXTFIELD }.first(). message
                ),
            )
        }
    }
}