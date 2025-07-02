package empire.digiprem.presentation.viewmodels
import androidx.lifecycle.viewModelScope
import empire.digiprem.data.config.DataSourceEventHandlerDecorator
import empire.digiprem.data.local.DataBaseTemp
import empire.digiprem.data.remote.service.FilesEndPointService
import empire.digiprem.dto.image.UploadProfileResponseDTO
import empire.digiprem.model.ApiResponse2
import empire.digiprem.model.AppFile
import empire.digiprem.presentation.intents.ProfileIntent
import empire.digiprem.presentation.models.ProfilModel
import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfilViewModel : AbstractViewModel<ProfilModel,ProfileIntent>(defaultState = ProfilModel()) {
    // TODO: ViewModel logic

  private val filesService= FilesEndPointService()

    init {
        _mutableState.update { it.copy(
            profileUrl = it.profileUrl.copy(url = DataBaseTemp.userProfile?.profileUrl?:""),
        ) }
    }

    override fun onIntentHandler(intent: ProfileIntent) {
        viewModelScope.launch {
            when(intent){
                ProfileIntent.OnClickPolicy -> TODO()
                is ProfileIntent.OnFirstNameChange -> TODO()
                is ProfileIntent.OnGenderSelect -> TODO()
                is ProfileIntent.OnLastNameChange -> TODO()
                is ProfileIntent.OnPhoneChange -> TODO()
                is ProfileIntent.OnProfilePictureChange ->onUploadProfilePicture(intent.newPicture)
                ProfileIntent.OnSubmit -> TODO()
                is ProfileIntent.OnTwoFactorAuthToggle -> TODO()
                is ProfileIntent.OnTwoFactorPrivacyToggle -> TODO()
            }
        }

    }

    private suspend fun onUploadProfilePicture(appFile: AppFile){

        collectDataSourceEvent(
            filesService.uploadProfileImage(appFile),
            object :DataSourceEventHandlerDecorator<UploadProfileResponseDTO>(){

                override suspend fun onLoading() {
                    _mutableState.update {
                        it.copy(
                            profileUrl = it.profileUrl.copy(isloading = true),
                        )
                    }
                }

                override suspend fun onSuccessConnexion(key: Any?, data: UploadProfileResponseDTO) {
                    _mutableState.update {
                        it.copy(
                            profileUrl = it.profileUrl.copy(url = data.imageUrl,isloading = false),
                        )
                    }
                }

                override suspend fun onFailConnexion(message: String) {
                    _mutableState.update {
                        it.copy(
                            profileUrl = it.profileUrl.copy(isloading = false),
                        )
                    }
                }

                override suspend fun onErrorConnexion(code: Int, messages: List<ApiResponse2.ErrorMessage>) {
                    _mutableState.update {
                        it.copy(
                            profileUrl = it.profileUrl.copy(isloading = false),
                        )
                    }
                }

                override suspend fun onFailProcess() {
                    _mutableState.update {
                        it.copy(
                            profileUrl = it.profileUrl.copy(isloading = false),
                        )
                    }
                }

            }
        )

    }


}