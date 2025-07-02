package empire.digiprem.presentation.components.forms

import androidx.compose.runtime.Composable
import empire.digiprem.app.config.Log
import empire.digiprem.model.AppFile

@Composable
actual fun PickStorageFiles(onResult: (List<AppFile>) -> Unit): Execute {
    return object : Execute {
        override fun execute() {
            TODO("Not yet implemented")
        }

    }
}

@Composable
actual fun PickStorageFile(input: String, onResult: (AppFile?) -> Unit): Execute {
    return object : Execute {
        override fun execute() {
            TODO("Not yet implemented")
        }

    }
}

actual fun TakePicture(onResult: (AppFile?) -> Unit): Execute {
    return object : Execute {
        override fun execute() {
            Log.i("TakePicture", "This feature is not implemented for ios.")
        }
    }
}