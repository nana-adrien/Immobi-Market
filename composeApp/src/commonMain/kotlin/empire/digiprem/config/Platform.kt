package empire.digiprem.config

enum class Platform {
    ANDROID,
    WEB,
    DESKTOP,
    IOS,
}

expect fun getPlatform():Platform
