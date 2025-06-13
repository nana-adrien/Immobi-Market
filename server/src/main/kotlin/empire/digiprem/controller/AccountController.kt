package empire.digiprem.controller

import empire.digiprem.dto.ForgotPasswordReq
import empire.digiprem.dto.ForgotPasswordRespDto
import empire.digiprem.dto.ResetPasswordReqDto
import empire.digiprem.dto.ResetPasswordRespDto
import empire.digiprem.extension.WrapApiResponse
import empire.digiprem.extension.success
import empire.digiprem.model.ApiResponse2
import empire.digiprem.services.UserAccountService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "accounts")
@RestController
@RequestMapping("/api/v1/accounts")
class AccountController(private val userAccountService: UserAccountService) {
    @WrapApiResponse
    @PostMapping("/verify-email")
    fun verifyEmail(@RequestBody email: String?): ApiResponse2<String> {
        return ApiResponse2.success("verifyEmail")
    }

    @PostMapping("/verify-phone-number")
    fun verifyPhoneNumber(@RequestParam email: String?): ApiResponse2<String> {
        return ApiResponse2.success("verifyPhoneNumber")
    }

    @PostMapping("/forgot-password")
    fun forgotPassword(@RequestBody forgotPasswordReq: ForgotPasswordReq): ApiResponse2<ForgotPasswordRespDto> {
        val user = userAccountService.forgotPassword(forgotPasswordReq.identity, forgotPasswordReq.isEmail)
        val message = "votre code de verification vous a ete envoyer sur votre "
        val canal = if (forgotPasswordReq.isEmail) " adresse email " else " numero de telephone "
        return ApiResponse2.success(ForgotPasswordRespDto(message + canal + forgotPasswordReq.identity))
    }

    @PostMapping("/reset-password")
    fun resetPassword(
        @RequestBody resetPasswordReqDto: ResetPasswordReqDto,
        @RequestHeader("RESET_CODE") resetCode: String
    ): ApiResponse2<ResetPasswordRespDto> {
        userAccountService.resetPassword(
            resetPasswordReqDto.identity,
            resetPasswordReqDto.isEmail,
            resetCode,
            resetPasswordReqDto.newPassword
        )
        return ApiResponse2.success(ResetPasswordRespDto("Reset password successful"))
    }

    @PostMapping("/change-password")
    fun changePassword(@RequestParam email: String?): String {
        return "changePassword"
    }
    @PostMapping("/change-email")
    fun changeEmail(@RequestParam email: String?): String {
        return "changePassword"
    }
}