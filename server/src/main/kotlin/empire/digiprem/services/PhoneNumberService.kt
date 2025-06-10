package empire.digiprem.services

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import empire.digiprem.models.UserPhoneNumber
import empire.digiprem.repositories.UserPhoneNumberRepository
import org.springframework.stereotype.Service

@Service
class PhoneNumberService(userPhoneNumberRepository: UserPhoneNumberRepository) {
    private val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()

    private val userPhoneNumberRepository: UserPhoneNumberRepository = userPhoneNumberRepository

    @Throws(NumberParseException::class)
    fun validatePhoneNumber(countryCode: String, regionCode: String, inputNumber: String?): Boolean {
        val number = countryCode.trim { it <= ' ' } + inputNumber?.trim { it <= ' ' }
        val phoneNumber = phoneUtil.parse(number, regionCode)
        return phoneUtil.isValidNumber(phoneNumber)
    }

    fun changePhoneNumber(userId: String?, oldPwd: String?, newPwd: String?) {
    }

    fun loadUserPhoneNumberByCountryCodeAndPhoneNumber(countryCode: String, phoneNumber: String): UserPhoneNumber {
        return userPhoneNumberRepository.findByCountryCodeAndPhoneNumber(countryCode, phoneNumber).orElse(null)
    }

    fun savePhoneNumber(userPhoneNumber: UserPhoneNumber): UserPhoneNumber {
        return userPhoneNumberRepository.save(userPhoneNumber)
    }
}