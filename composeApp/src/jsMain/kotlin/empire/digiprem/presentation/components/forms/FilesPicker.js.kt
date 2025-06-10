package octopusfx.client.mobile.core.ui.screens.components.forms

import androidx.compose.runtime.Composable

@Composable
actual fun PickAndCropImageContract(
    input: String,
    onResult: (AppFile?) -> Unit
): ExecutePickAndCropImage {

}

@Composable
actual fun PickStorageFile(onResult: (AppFile?) -> Unit): ExecuteInput<String> {
    TODO("Not yet implemented")
}

@Composable
actual fun PickStorageFiles(onResult: (List<AppFile>) -> Unit): ExecuteInput<String> {
    TODO("Not yet implemented")
}