package empire.digiprem.service

import empire.digiprem.models.UserAwaitingVerification
import empire.digiprem.models.UserPhoneNumber
import empire.digiprem.models.Users
import empire.digiprem.models.VerificationOperation


interface IUserAccountService {
    fun createUserAccount(email: String, password: String): Users
    fun createUserAccount(user: Users): Users
    fun validateEmail(email: String): Boolean

    fun validateEmail2(email: String): String

    fun validatePhoneNumber(phoneNumber: String, countryCode: String): Boolean

    fun validatePhoneNumber2(phoneNumber: String, countryCode: String): String

    fun validatePassword(password: String): Boolean

    fun generateVerificationCode(): String

    fun generateResetCode(): String

    fun verifyCode(inputCode: String, actualCode: String): Boolean

    fun sendVerificationCode(
        identity: String,
        password: String?,
        isEmail: Boolean,
        subject: String,
        operation: VerificationOperation
    ): Boolean

    fun sendVerifyIdentityPinCode(
        identity: String,
        password: String,
        isEmail: Boolean,
        subject: String,
        operation: VerificationOperation
    ): UserAwaitingVerification

    fun resendVerificationCode(identity: String): Boolean

    fun deleteUserAwaitingVerification(userAwaitingVerification: UserAwaitingVerification)

    fun verifyCode(
        identity: String,
        verificationCode: String,
        operation: VerificationOperation
    ): UserAwaitingVerification

    fun verifyPinCode(identity: String, verificationCode: String): UserAwaitingVerification?

    fun loadUserByIdentity(identity: String, isEmail: Boolean): Users?
    fun loadUserByIdentityEx(identity: String, countryCode: String, isEmail: Boolean): Users?

    fun createPhoneNumber(userPhoneNumber: UserPhoneNumber): UserPhoneNumber?
}
