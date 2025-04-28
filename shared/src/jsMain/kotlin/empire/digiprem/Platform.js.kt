package empire.digiprem


class JsApplication : Platform {
    override val name: String = "js"
}

actual fun getPlatform(): Platform {
   return JsApplication()
}