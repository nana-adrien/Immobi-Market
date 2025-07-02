package empire.digiprem.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import empire.digiprem.data.local.entities.Utilisateur
import empire.digiprem.data.config.DataSourceEventHandlerDecorator
import empire.digiprem.data.remote.service.ProfileService
import empire.digiprem.dto.profile.GetProfileResponse
import empire.digiprem.model.ApiResponse2
import empire.digiprem.navigation.ViewHome
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import empire.digiprem.presentation.intents.SuccessfulAuthIntent
import empire.digiprem.presentation.models.SuccessfulAuthModel
import empire.digiprem.presentation.viewmodels.componenet.SessionManager
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SuccessfulAuthViewModel(val navController: NavController,val sessionManager: SessionManager) :
    AbstractViewModel<SuccessfulAuthModel, SuccessfulAuthIntent>(defaultState = SuccessfulAuthModel()) {
    // TODO: ViewModel logic

    private val profileService = ProfileService()

        init {
            onIntentHandler(SuccessfulAuthIntent.OnContinue)
        }

    override fun onIntentHandler(intent: SuccessfulAuthIntent) {
        viewModelScope.launch {
            when (intent) {
                SuccessfulAuthIntent.OnContinue -> {
                    configUserProfile()
                }
            }
        }
    }

    private suspend fun configUserProfile() {
        collectDataSourceEvent(
            profileService.getProfile(),
            object : DataSourceEventHandlerDecorator<GetProfileResponse>() {
                override suspend fun onLoading() {
                    super.onLoading()
                    _mutableState.update {
                        it.copy(
                            enabledContinueButton = false,
                        )
                    }
                }
                override suspend fun onSuccessConnexion(key: Any?, data: GetProfileResponse) {
                    super.onSuccessConnexion(key, data)
                    mainScope.launch {
                        sessionManager.setUtilisateur(
                            Utilisateur(
                                nom = data.name,
                                email = data.email,
                                photo = data.profileUrl,
                                role = data.role,)
                        )
                        navController.navigate(ViewHome()) // Navigate to home screen
                    }
                }
                override suspend fun onFailConnexion(message: String) {
                    super.onFailConnexion(message)
                    _mutableState.update {
                        it.copy(
                           enabledContinueButton = true,
                        )
                    }
                }

                override suspend fun onErrorConnexion(code: Int, messages: List<ApiResponse2.ErrorMessage>) {
                    super.onErrorConnexion(code, messages)
                    _mutableState.update {
                        it.copy(
                            enabledContinueButton = true,
                        )
                    }
                }

                override suspend fun onFailProcess() {
                    super.onFailProcess()
                    _mutableState.update {
                        it.copy(
                            enabledContinueButton = true,
                        )
                    }
                }
            }

        )
    }
}

