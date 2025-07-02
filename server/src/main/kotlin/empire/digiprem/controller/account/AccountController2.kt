package empire.digiprem.controller.account

import empire.digiprem.dto.account.CreateAccountRequestDTO
import empire.digiprem.dto.account.CreateAccountResponse
import empire.digiprem.dto.account.createAccountRequestDTOValidation
import empire.digiprem.enums.FileUriTypeEnum
import empire.digiprem.extension.success
import empire.digiprem.extension.validationError
import empire.digiprem.model.ApiResponse2
import empire.digiprem.model.AppFile
import empire.digiprem.models.database.User
import empire.digiprem.services.file.FilesService
import empire.digiprem.services.auth.UserAccountService
import empire.digiprem.utils.tools
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.uuid.ExperimentalUuidApi

@Tag(name = "accounts")
@RestController
@RequestMapping("/api/v2/account")
class AccountController2(
    private val accountService: UserAccountService,
    private val filesService: FilesService
) {


    @OptIn(ExperimentalUuidApi::class)
    @PostMapping("/create")
    fun createAccount(@RequestBody body:CreateAccountRequestDTO,@AuthenticationPrincipal userDetails: UserDetails): ApiResponse2<CreateAccountResponse> {
        val validate = createAccountRequestDTOValidation.validate(body)
        if (!validate.isValid){
            return ApiResponse2.validationError(validate.errors)
        }

        val appFile= AppFile(
            byteArray = tools.createAvatarImage(body.lastName) ,//file.bytes,
            mimeType = FileUriTypeEnum.IMAGE_JPEG ,
            mimeTypeDescriptor ="image/jpeg",
            name =(userDetails as User).id.toString()  ,
        )
        val profileUrl=filesService.uploadProfile(userDetails.email,appFile)
        val user= accountService.createAccount(userDetails.email ,body,profileUrl)
        return ApiResponse2.success(CreateAccountResponse("Utilisateur créé avec succès"))
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