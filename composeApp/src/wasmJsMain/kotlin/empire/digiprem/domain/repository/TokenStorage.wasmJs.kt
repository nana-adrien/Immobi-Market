package empire.digiprem.domain.repository

import kotlinx.browser.window

class WasmJsTokenStorage:TokenStorage{
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