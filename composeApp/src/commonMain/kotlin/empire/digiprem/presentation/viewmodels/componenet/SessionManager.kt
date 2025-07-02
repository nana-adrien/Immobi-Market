package empire.digiprem.presentation.viewmodels.componenet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import empire.digiprem.app.config.Log
import empire.digiprem.data.local.entities.Utilisateur
import empire.digiprem.core.helpers.getAccessTokenClaims
import empire.digiprem.data.config.BaseDataSourceEventHandler
import empire.digiprem.data.config.DataSourceEventCollector
import empire.digiprem.data.local.repository.UserRepository
import empire.digiprem.data.remote.service.OAuthEndPointEndPointService
import empire.digiprem.domain.repository.IUserRepository
import empire.digiprem.domain.repository.TokenStorage
import empire.digiprem.dto.auth.refresh_token.RefreshTokenResponseDTO
import empire.digiprem.enums.TokenEnum
import empire.digiprem.model.ApiResponse2
import empire.digiprem.model.TokenHelper
import empire.digiprem.model.TokensResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class SessionManager(
    val userRepository: IUserRepository,
    private val tokenStorage: TokenStorage,
    private val oAuthEndPointEndPointService: OAuthEndPointEndPointService
) : ViewModel(), KoinComponent {
    private val _utilisateur = MutableStateFlow<Utilisateur?>(null)
    val utilisateur: StateFlow<Utilisateur?> = _utilisateur.asStateFlow()
    private val _isStarted = MutableStateFlow(false)
    val isStarted = _isStarted.asStateFlow()
    private val _enabledLogOutDialog = MutableStateFlow(false)
    val enabledLogOutDialog = _enabledLogOutDialog.asStateFlow()

    private val dataSourceEventCollector = DataSourceEventCollector()

    init {
        viewModelScope.launch {
            while (true) {
                val token = getToken()
                if (token != null) {
                    if (!isTokenExpired(token)) {
                        if (userRepository.getUser() != null) {
                            _utilisateur.value = userRepository.getUser()
                            _isStarted.value = true
                        }
                    } else {
                        val refreshToken = getRefreshToken()
                        if (refreshToken != null) {
                            Log.e("refreshtoken", refreshToken.toString())
                            if (!isTokenExpired(refreshToken)) {
                                Log.e("refreshtoken", refreshToken.toString())
                                refreshToken(refreshToken)
                            } else {
                                logOut()
                            }
                        } else {
                            logOut()
                        }
                    }
                }
                delay(30 * 1000L)
            }
        }
    }

    suspend fun refreshToken(refreshToken: String) {
        dataSourceEventCollector.collectEvent(
            event = oAuthEndPointEndPointService.refreshToken(refreshToken),
            object : BaseDataSourceEventHandler<RefreshTokenResponseDTO>() {
                override suspend fun onLoading() {
                }

                override suspend fun onFailConnexion(message: String) {
                    Log.e("refreshToken", message)
                    logOut()
                }

                override suspend fun onErrorConnexion(code: Int, messages: List<ApiResponse2.ErrorMessage>) {
                    Log.e("refreshToken", messages.toString())
                    logOut()
                }

                override suspend fun onFailProcess() {
                    logOut()
                }

                override suspend fun onSuccessConnexion(key: Any?, data: RefreshTokenResponseDTO) {
                    clearTokens()
                    saveToken(data.tokensResult)
                }
            }
        )

    }

    private suspend fun clearTokens() {
        tokenStorage.clearToken(TokenEnum.ACCESS_TOKEN)
        tokenStorage.clearToken(TokenEnum.REFRESH_TOKEN)
    }

    @OptIn(ExperimentalTime::class)
    fun isTokenExpired(token: String): Boolean {
        val expirationTime = 1000 * TokensResult.getAccessTokenClaims(token, TokenHelper.EXPIRATION_TIME).toLong()
        if (expirationTime < Clock.System.now()
                .toEpochMilliseconds()
        ) {
            return true
        }
        return false
    }

    suspend fun setUtilisateur(user: Utilisateur) {
        _isStarted.value = true
        _utilisateur.value = user
        userRepository.saveUser(user)
    }

    fun updateNom(nouveauNom: String) {
        _utilisateur.update { it?.copy(nom = nouveauNom) }
    }

    fun logOut() {
        viewModelScope.launch {
            _isStarted.value = false
            if (_utilisateur.value != null) {
                _utilisateur.value = null
                _enabledLogOutDialog.value = true
                userRepository.deleteUser()
            }
            clearTokens()
        }
    }

    fun diseableDialogSessionExpirateMessage() {
        _enabledLogOutDialog.value = false
    }

    fun clear() {
        _utilisateur.value = null
    }

    suspend fun getToken(): String? {
        return tokenStorage.getToken(TokenEnum.ACCESS_TOKEN)
    }

    private suspend fun getRefreshToken(): String? {
        return tokenStorage.getToken(TokenEnum.REFRESH_TOKEN)
    }

    suspend fun saveToken(tokensResult: TokensResult) {
        tokenStorage.saveToken(TokenEnum.ACCESS_TOKEN, tokensResult.accessToken)
        tokenStorage.saveToken(TokenEnum.REFRESH_TOKEN, tokensResult.refreshToken)
    }

    suspend fun getUser() {
        _utilisateur.value = userRepository.getUser()
    }
     fun changeTheme() {
         viewModelScope.launch {
             val oldThemeMode = _utilisateur.value?.enabledlightMode == true
             _utilisateur.value?.let {
                 setUtilisateur(user =it.copy(enabledlightMode = !oldThemeMode))
             }
         }
    }
}
