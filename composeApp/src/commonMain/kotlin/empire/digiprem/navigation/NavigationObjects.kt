package empire.digiprem.navigation

// initialisation navigation object
import kotlinx.serialization.Serializable

@Serializable
data class Home(val isConnected: Boolean = false, val productItem: String = "", val scrollPosition: Int = 0)
