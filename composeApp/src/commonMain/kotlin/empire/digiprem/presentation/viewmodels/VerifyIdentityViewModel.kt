package empire.digiprem.presentation.viewmodels

import androidx.navigation.NavController
import empire.digiprem.app.config.Log
import empire.digiprem.core.helpers.getAccessTokenClaims
import empire.digiprem.data.config.DataSourceEventHandlerDecorator
import empire.digiprem.data.local.DataBaseTemp
import empire.digiprem.data.remote.service.OAuthEndPointEndPointService
import empire.digiprem.domain.repository.TokenStorage
import empire.digiprem.dto.auth.verify_identity.VerifyIdentityResponseDTO
import empire.digiprem.enums.TokenEnum
import empire.digiprem.enums.VerificationOperation
import empire.digiprem.model.ApiResponse2
import empire.digiprem.model.TokenHelper
import empire.digiprem.model.TokensResult
import empire.digiprem.navigation.ViewCompleteAccount
import empire.digiprem.navigation.ViewHome
import empire.digiprem.navigation.ViewSuccessfulAuth
import empire.digiprem.presentation.intents.VerifyIdentityIntent
import empire.digiprem.presentation.models.VerifyIdentityModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class VerifyIdentityViewModel(val navController: NavController) :
    AbstractViewModel<VerifyIdentityModel, VerifyIdentityIntent>(defaultState = VerifyIdentityModel()) ,KoinComponent{
    // TODO: ViewModel logic
    private val oauthEndPointService = OAuthEndPointEndPointService()
    private val tokenStorage: TokenStorage by inject()
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
                        pinCode = _mutableState.value.pinCode.value,
                        operationType = intent.operationType
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
                enableSendButton = state.pinCode.value.length == 6
            )
        }
    }

    suspend fun onVerifyIdentity(
        identity: String,
        pinCode: String,
        operationType: String
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
                    mainScope.launch {
                        when (VerificationOperation.valueOf(operationType)) {
                            VerificationOperation.REGISTRATION -> {
                                Log.i("AuthRespDto", "data=$data")
                                val token = data.tokens.accessToken
                                saveToken(data.tokens)
                                TokensResult.getAccessTokenClaims(token, TokenHelper.IDENTITY)
                                navController.navigate(
                                    ViewCompleteAccount(
                                        email = TokensResult.getAccessTokenClaims(
                                            token,
                                            TokenHelper.IDENTITY
                                        )
                                    )
                                )
                            }
                            else -> {
                                DataBaseTemp.isConnected=true
                                saveToken(data.tokens)
                                navController.navigate(ViewSuccessfulAuth())
                            }
                        }
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
                    errorMessage = errorMessage.first { it.field == PINCODE_TEXTFIELD }.message
                ),
            )
        }
    }

    suspend fun saveToken(token: TokensResult) {
        tokenStorage.saveToken(TokenEnum.ACCESS_TOKEN,token.accessToken)
        tokenStorage.saveToken(TokenEnum.REFRESH_TOKEN,token.refreshToken)
    }
}