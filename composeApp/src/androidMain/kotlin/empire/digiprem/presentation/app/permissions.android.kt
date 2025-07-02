package empire.digiprem.presentation.app

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import empire.digiprem.domain.enums.PermissionStateEnum
import empire.digiprem.presentation.components.app.PermissionDelegate
import empire.digiprem.presentation.components.forms.Execute

open class AndroidGlobalPermissionDelegate(
    private val condition: Boolean = true,
    val context: Context,
    val permissionName: String
) : PermissionDelegate {
    private final val ONE_PERMISSION = 1
    override fun getPermissionState(): PermissionStateEnum {
        if (!condition) {
            return PermissionStateEnum.GRANTED
        }
        return if (checkMultiplePermission()) PermissionStateEnum.GRANTED else PermissionStateEnum.DENIED
    }
    override suspend fun providePermission() {
        TODO("Not yet implemented")
    }
    override fun openSettingPage() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    override fun enablePermission(): Boolean {
        return condition
    }

    @Composable
    override fun LaunchPermissionRequest(onResult: (PermissionStateEnum) -> Unit): Execute {
        if (!condition) {
            onResult(PermissionStateEnum.GRANTED)
            return object : Execute {
                override fun execute(input: String) {
                    TODO("Not yet implemented")
                }
            }
        }
        if (permissionName.split("-").size > ONE_PERMISSION) {
            val requestPermissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (isGranted) {
                        onResult(PermissionStateEnum.GRANTED)
                    } else {
                        onResult(PermissionStateEnum.DENIED)
                    }
                }
            return object : Execute { override fun execute(input: String) { requestPermissionLauncher.launch(getPermissionsNames().first()) } }
        }
        val requestPermissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
                if (isGranted.any { it.value }) {
                    onResult(PermissionStateEnum.GRANTED)
                } else {
                    onResult(PermissionStateEnum.DENIED)
                }
            }
        return object : Execute {
            override fun execute(input: String)  {
                requestPermissionLauncher.launch(getPermissionsNames().toTypedArray())
            }
        }
    }

    private fun getPermissionsNames(): List<String> {
        return permissionName.split("&&").map {it.trim()}
    }
    private fun checkMultiplePermission(): Boolean {
        return getPermissionsNames().all {
            ContextCompat.checkSelfPermission(context, it) ==
                    android.content.pm.PackageManager.PERMISSION_GRANTED
        }
    }
}

class CameraOnPermissionDelegate( context: Context) : AndroidGlobalPermissionDelegate(context=context, permissionName = Manifest.permission.CAMERA)
class ReadExternalStorageOnPermissionDelegate( context: Context):
    AndroidGlobalPermissionDelegate(context=context, permissionName = Manifest.permission.READ_EXTERNAL_STORAGE)
@SuppressLint("InlinedApi")
class PostNotificationPermissionDelegate(context: Context) :
    AndroidGlobalPermissionDelegate(
        condition = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU,
        context = context,
        permissionName =Manifest.permission.POST_NOTIFICATIONS
    )
@SuppressLint("InlinedApi")
class AccessBackgroundLocationPermissionDelegate(context: Context) :
    AndroidGlobalPermissionDelegate(
        condition = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q,
        context = context,
        permissionName = Manifest.permission.ACCESS_BACKGROUND_LOCATION
    )
class AccessFineAndCoarseLocationPermissionDelegate(context: Context) : AndroidGlobalPermissionDelegate(
    context = context,
    permissionName = "${Manifest.permission.ACCESS_FINE_LOCATION} && ${Manifest.permission.ACCESS_COARSE_LOCATION}"
)


