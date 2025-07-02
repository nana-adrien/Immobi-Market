package empire.digiprem.config

enum class Platform {
    ANDROID,
    WEB,
    DESKTOP,
    IOS;

    fun isMobilePlatforme():Boolean{
        return this == ANDROID || this == IOS
    }
}

expect fun getPlatform():Platform
