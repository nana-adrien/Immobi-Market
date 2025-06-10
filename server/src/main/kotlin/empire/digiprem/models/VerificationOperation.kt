package empire.digiprem.models

enum class VerificationOperation(val code: Int) {
    AUTHENTICATION(0),
    REGISTRATION(1),
    NOT_VERIFY(2),
    VERIFY(3)
}