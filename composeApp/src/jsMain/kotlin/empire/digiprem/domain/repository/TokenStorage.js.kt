package empire.digiprem.domain.repository
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.WindowLocalStorage
import org.w3c.dom.get

class JsTokenStorage:TokenStorage{
    override suspend fun saveToken(token: String) {
        // Save token as a cookie with a max age of 7 days
       window.localStorage.setItem("key", token,)
        //cookieStorage.saveCookie("key", token, 7 * 24 * 60 * 60)
    }
    override suspend fun getToken(): String? {
        //return cookieStorage.getCookie("key")
        return window.localStorage.getItem("key")
    }
    override suspend fun clearToken() {
        window.localStorage.removeItem("key")
    }
}
interface CookieStorage {
    fun saveCookie(name: String, value: String, maxAge: Int)
    fun getCookie(name: String): String?
    fun clearCookie(name: String)
}


class JsCookieStorage : CookieStorage {
    override fun saveCookie(name: String, value: String, maxAge: Int) {
        document.cookie = "$name=$value; max-age=$maxAge"
    }
    override fun getCookie(name: String): String? {
        return document.cookie
            .split("; ")
            .find { it.startsWith("$name=") }
            ?.substringAfter("$name=")
    }
    override fun clearCookie(name: String) {
        document.cookie = "$name=; max-age=0"
    }
}