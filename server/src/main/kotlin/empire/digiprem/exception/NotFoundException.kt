package empire.digiprem.exception

import com.google.api.gax.rpc.NotFoundException
import org.springframework.security.core.userdetails.UsernameNotFoundException

class NotFoundException(message:String):Exception(message) {
}