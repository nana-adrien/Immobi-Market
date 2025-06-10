package empire.digiprem.dto.auth.authentication;

import java.io.Serializable;

public data class AuthenticationRequestDTO(val identity :String, val password:String,val isEmail:Boolean){
}
