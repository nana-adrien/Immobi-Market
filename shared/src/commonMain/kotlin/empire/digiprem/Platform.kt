package empire.digiprem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform