package empire.digiprem.presentation.components.app

import androidx.compose.runtime.Composable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import empire.digiprem.domain.enums.PermissionEnum
import empire.digiprem.domain.enums.PermissionStateEnum
import empire.digiprem.presentation.components.app.PermissionsService.Companion.PERMISSION_CHECK_FLOW_FREQUENCY
import empire.digiprem.presentation.components.forms.Execute
import org.koin.core.component.KoinComponent


interface PermissionsService {
    fun checkPermission(permissionEnum: PermissionEnum): PermissionStateEnum
    fun checkPermissionFlow(permissionEnum: PermissionEnum): Flow<PermissionStateEnum>
    suspend fun providePermission(permissionEnum: PermissionEnum)
    fun openSettingPage(permissionEnum: PermissionEnum)

    @Composable
    fun launchRequestPermission(
        permissionEnums: List<PermissionEnum>,
        onResult: (PermissionStateEnum) -> Unit
    ): Execute

    @Composable
    fun launchRequestMultiplePermission(
        permissionEnums: List<PermissionEnum>,
        onResult: (PermissionStateEnum) -> Unit
    ): Execute

    companion object {
        const val PERMISSION_CHECK_FLOW_FREQUENCY = 1000L
    }
}

internal class PermissionsServiceImpl : PermissionsService, KoinComponent {

    private val launcher: Execute? = null

    override fun checkPermission(permissionEnum: PermissionEnum): PermissionStateEnum {
        return try {
            return getPermissionDelegate(permissionEnum).getPermissionState()
        } catch (e: Exception) {
            println("Failed to check permission $permissionEnum")
            e.printStackTrace()
            PermissionStateEnum.NOT_DETERMINED
        }
    }

    override fun checkPermissionFlow(permissionEnum: PermissionEnum): Flow<PermissionStateEnum> {
        return flow {
            while (true) {
                val permissionState = checkPermission(permissionEnum)
                emit(permissionState)
                delay(PERMISSION_CHECK_FLOW_FREQUENCY)
            }
        }
    }

    override suspend fun providePermission(permissionEnum: PermissionEnum) {
        try {
            getPermissionDelegate(permissionEnum).providePermission()
        } catch (e: Exception) {
            println("Failed to request permission $permissionEnum")
            e.printStackTrace()
        }
    }

    override fun openSettingPage(permissionEnum: PermissionEnum) {
        println("Open settings for permission $permissionEnum")
        try {
            getPermissionDelegate(permissionEnum).openSettingPage()
        } catch (e: Exception) {
            println("Failed to open settings for permission $permissionEnum")
            e.printStackTrace()
        }
    }


    @Composable
    override fun launchRequestPermission(
        permissionEnums: List<PermissionEnum>,
        onResult: (PermissionStateEnum) -> Unit
    ): Execute {
        var count = 0
        val map: Map<PermissionEnum, PermissionStateEnum> = mutableMapOf()
        val permissionsStates = permissionEnums.map {
            getPermissionDelegate(it).LaunchPermissionRequest({ state ->
                count++
                map.plus(mapOf(it to state))
            }
            )
        }

        return object : Execute {
            override fun execute(input:String) {
                permissionsStates.forEach { it.execute() }
                if (count == permissionsStates.size && map.values.all { !it.notGranted() }) {
                    onResult(PermissionStateEnum.GRANTED)
                } else {
                    onResult(PermissionStateEnum.DENIED)
                }
            }
        }
    }

    @Composable
    fun execute(
        permissionDelegate: List<PermissionDelegate>,
        onResult: (PermissionStateEnum) -> Unit
    ): Execute? {

        var index = 0
        var execute: Execute? = null

        if (index < permissionDelegate.size) {
            var isGranted = false
            execute =
                permissionDelegate[index++].LaunchPermissionRequest { isGranted = it.notGranted() }
            if (isGranted.not()) {
                onResult(PermissionStateEnum.DENIED)
            } else {
                execute(permissionDelegate.filter { it!= permissionDelegate[index++] }, onResult)
            }
            return execute
        }
        return null
    }

    @Composable
    override fun launchRequestMultiplePermission(
        permissionEnums: List<PermissionEnum>,
        onResult: (PermissionStateEnum) -> Unit
    ): Execute {
        val permissions =
            permissionEnums.map { getPermissionDelegate(it) }.filter { it.enablePermission() }

        return object : Execute {
            override fun execute(input:String) {

            }
        }
    }


    private fun getPermissionDelegate(permissionEnum: PermissionEnum): PermissionDelegate {
        return when (permissionEnum) {
            PermissionEnum.NOTING -> throw RuntimeException("no specify permission")
           // PermissionEnum.CAMERA -> getKoin().get(CAMERA_PERMISSION)
        //    PermissionEnum.READ_EXTERNAL_STORAGE -> getKoin().get(READ_EXTERNAL_STORAGE_PERMISSION)
          //  PermissionEnum.ACCESS_BACKGROUND_LOCATION -> getKoin().get(ACCESS_BACKGROUND_LOCATION)
         //   PermissionEnum.POST_NOTIFICATIONS -> getKoin().get(POST_NOTIFICATIONS)
         //   PermissionEnum.ACCESS_FINE_LOCATION_AND_ACCESS_COARSE_LOCATION -> getKoin().get(
         //       ACCESS_FINE_LOCATION_AND_ACCESS_COARSE_LOCATION
         //   )

         //   PermissionEnum.MULTIPLE_PERMISSION -> getKoin().get(
              //  ACCESS_FINE_LOCATION_AND_ACCESS_COARSE_LOCATION
            ///)
            else->{
                TODO()
            }
        }
    }

}

interface PermissionDelegate {
    fun getPermissionState(): PermissionStateEnum
    suspend fun providePermission()
    fun openSettingPage()
    fun enablePermission(): Boolean

    @Composable
    fun LaunchPermissionRequest(onResult: (PermissionStateEnum) -> Unit): Execute
}


/*

 class CameraOnPermissionDelegate: PermissionDelegate

 class ReadExternalStorageOnPermissionDelegate: PermissionDelegate*/
