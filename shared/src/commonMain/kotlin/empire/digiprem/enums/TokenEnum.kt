package empire.digiprem.enums

enum class TokenEnum{
    ACCESS_TOKEN,
    REFRESH_TOKEN;

    fun isRefreshToken(): Boolean {
        return this == REFRESH_TOKEN}
}