package empire.digiprem.data.remote.config

import empire.digiprem.config.getPlatform
import empire.digiprem.config.isCompactMobilePlatform

object HttpConstants {
     val BASE_URL = if(!getPlatform().isMobilePlatforme())  "http://localhost:8090/api/" else "http://192.168.137.1:8090/api/"
}