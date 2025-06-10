package empire.digiprem.controller

import empire.digiprem.extension.success
import empire.digiprem.model.ApiResponse2
import empire.digiprem.models.Users
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

//import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "settings")
@RestController
@RequestMapping("/api/v1/settings")
class UserSettingsControllers {
    @get:GetMapping("/get-user-settings")
    val userSettings: ApiResponse2<Users>
        get() = ApiResponse2.success(Users())

    @PostMapping("/save-user-Settings")
    fun saveUserSettings(@RequestBody user: String?): ApiResponse2<Users> {
        return ApiResponse2.success(Users())
    }
}