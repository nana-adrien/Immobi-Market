package empire.digiprem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
               /* modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(stateVertical)*/
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppAndroidPreview() {
    App(

    )
}