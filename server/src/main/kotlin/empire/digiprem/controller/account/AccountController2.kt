package empire.digiprem.controller.account

import empire.digiprem.dto.account.CreateAccountRequestDTO
import empire.digiprem.dto.account.CreateAccountResponse
import empire.digiprem.dto.account.createAccountRequestDTOValidation
import empire.digiprem.extension.success
import empire.digiprem.extension.validationError
import empire.digiprem.model.ApiResponse2
import empire.digiprem.services.UserAccountService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/account")
class AccountController2(
    private val accountService: UserAccountService,
) {


    @PostMapping("/create")
    fun createAccount(@RequestBody body:CreateAccountRequestDTO,@AuthenticationPrincipal userDetails: UserDetails): ApiResponse2<CreateAccountResponse> {
        val validate = createAccountRequestDTOValidation.validate(body)
        if (!validate.isValid){
            return ApiResponse2.validationError(validate.errors)
        }
        val user= accountService.createAccount(userDetails.username,body)
        return ApiResponse2.success(CreateAccountResponse("Utilisateur ${user.name} créé avec succès"))
    }

    @PostMapping("/verify-phone-number")
    fun verifyPhoneNumber(): ApiResponse2<String> {
        return ApiResponse2.success("")
    }

    @PostMapping("/change-password")
    fun changePassword(): ApiResponse2<String> {
        return ApiResponse2.success("")
    }


}