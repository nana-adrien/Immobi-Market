package empire.digiprem.domain.repository

import empire.digiprem.data.local.DataBaseTemp.token
import empire.digiprem.enums.TokenEnum
import kotlinx.browser.document
import kotlinx.browser.window

fun getExpirateTime(): String =  js("new Date(Date.now() + 7 * 24 * 60 * 60 * 1000).toUTCString()")

class WasmJsTokenStorage:TokenStorage{
    override suspend fun saveToken(key: TokenEnum, token: String) {
        // Save token as a cookie with a max age of 7 days
        when(key){
             TokenEnum.ACCESS_TOKEN -> {
                window.localStorage.setItem(key.name, token)
            }
            TokenEnum.REFRESH_TOKEN -> {
                document.cookie = "refreshToken=$token; path=/; expires=${getExpirateTime()}; secure; samesite=strict"
            }
        }

       //.saveCookie(key.name, token, 7 * 24 * 60 * 60)
    }

    override suspend fun getToken(key: TokenEnum): String? {
      return when(key){
            TokenEnum.ACCESS_TOKEN -> {
                window.localStorage.getItem(key.name)
            }
            TokenEnum.REFRESH_TOKEN -> {
                val cookies = document.cookie.split("; ")
                cookies.firstOrNull { it.startsWith("refreshToken=") }?.substringAfter("=")
            }

        }
    }

    override suspend fun isValidToken(token: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun clearToken(key: TokenEnum) {
        when(key){
            TokenEnum.ACCESS_TOKEN -> {
                window.localStorage.getItem(key.name)
            }
            TokenEnum.REFRESH_TOKEN -> {
                val cookies = document.cookie.split("; ")

            }

        }
    }
}