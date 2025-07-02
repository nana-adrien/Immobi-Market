package empire.digiprem.controller.profile

import empire.digiprem.dto.profile.GetProfileResponse
import empire.digiprem.dto.profile.UpdateProfileRequest
import empire.digiprem.dto.profile.UpdateProfileResponse
import empire.digiprem.extension.success
import empire.digiprem.model.ApiResponse2
import empire.digiprem.models.database.User
import empire.digiprem.services.ProfileService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@Tag(name = "profile")
@RestController
@RequestMapping("api/v2/profile")
class ProfileController(
  private val profileService: ProfileService
){

    /*@PutMapping("/update")
    fun updateProfile(@RequestBody request: UpdateProfileRequest,@AuthenticationPrincipal userDetails: UserDetails): ApiResponse2<UpdateProfileResponse> {
        return ApiResponse2.success(profileService.updateProfile((userDetails as User ).email,request))
    }*/
    @GetMapping("/get")
    fun getProfile(@AuthenticationPrincipal userDetails: UserDetails): ApiResponse2<GetProfileResponse> {
        return ApiResponse2.success(profileService.getProfile((userDetails as User)))
    }
    @GetMapping("/get/{userEmail}")
    fun getProfileByUserEmail(@PathVariable userEmail: String): ApiResponse2<GetProfileResponse> {
        return ApiResponse2.success(profileService.getProfileBYUserId(userEmail))
    }

}
