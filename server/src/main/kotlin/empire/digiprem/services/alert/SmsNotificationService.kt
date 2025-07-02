package empire.digiprem.services.alert

import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class SmsNotificationService {
    @PostConstruct
    fun init() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN)
    }

    fun sendMessage(phoneNumber: String?, message: String?) {
        Message.creator(
            PhoneNumber(phoneNumber), PhoneNumber(
                FORM_NUMBER
            ), message
        ).create()
    }

    companion object {
        private const val ACCOUNT_SID = "AC8bcd81f84a0ab576cb6b39f60eda76d5"
        private const val AUTH_TOKEN = ""
        private const val FORM_NUMBER = ""
    }
}