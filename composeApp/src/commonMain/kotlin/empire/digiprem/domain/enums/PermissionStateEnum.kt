package empire.digiprem.domain.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import empire.digiprem.domain.enums.UIPermissionStateHandler.Companion.getUiState
import empire.digiprem.presentation.components.app.PermissionsService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


enum class PermissionEnum {
    NOTING,
    CAMERA,
    MULTIPLE_PERMISSION,
    READ_EXTERNAL_STORAGE,
    ACCESS_BACKGROUND_LOCATION,
    ACCESS_FINE_LOCATION_AND_ACCESS_COARSE_LOCATION,
    POST_NOTIFICATIONS;

    fun getUIState(): UIPermissionState {
      return  getUiState(this)
    }
    fun geResourceIcon(permission: PermissionEnum): ImageVector {
        return when (permission) {
            NOTING -> TODO()
            CAMERA -> Icons.Default.Camera
            READ_EXTERNAL_STORAGE -> Icons.Default.Storage
            ACCESS_BACKGROUND_LOCATION ->Icons.Default.LocationCity
            ACCESS_FINE_LOCATION_AND_ACCESS_COARSE_LOCATION ->Icons.Default.LocationOn
            POST_NOTIFICATIONS ->Icons.Default.Notifications
            MULTIPLE_PERMISSION -> TODO()
        }
    }
}

class UIPermissionStateHandler(){
    companion object: KoinComponent{
        fun getUiState(permissionEnum: PermissionEnum): UIPermissionState {
            val permissionsService by inject<PermissionsService>()
            return  UIPermissionState(
                name = permissionEnum.name,
                permission = permissionEnum,
                icon =permissionEnum.geResourceIcon(permissionEnum),
                isGranted = permissionsService.checkPermission(permissionEnum) == PermissionStateEnum.GRANTED
            )
        }
    }
}

data class UIPermissionState (
    val name: String,
    val permission: PermissionEnum,
    val icon: ImageVector,
    val isGranted: Boolean
) {
    fun notGranted(): Boolean {
        return !isGranted
    }
}


enum class PermissionStateEnum {
    GRANTED,
    DENIED,
    NOT_DETERMINED;

    fun notGranted(): Boolean {
        return this != GRANTED
    }
}