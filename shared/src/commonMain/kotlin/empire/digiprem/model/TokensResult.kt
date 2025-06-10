package empire.digiprem.model

import kotlinx.serialization.Serializable


@Serializable
data class TokensResult(val accessToken :String="",val refreshToken:String=""){
    companion object{

    }
}