package empire.digiprem.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun currentDateFormatter(dateFormat: String = "yyyy_MM_dd_HH_mm_ss_SSS"): String {
        val currentDateTime = LocalDateTime.now()

        val formatter =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS") // Format: année_mois_jour_heure_minute_seconde_millième
        return currentDateTime.format(formatter)
    }
}