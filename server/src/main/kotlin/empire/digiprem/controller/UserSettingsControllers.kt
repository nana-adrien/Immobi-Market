package empire.digiprem.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

//import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "settings")
@RestController
@RequestMapping("/api/v1/settings")
class UserSettingsControllers {
    /*@get:GetMapping("/get-user-settings")
    val userSettings: ApiResponse2<User>
        get() = ApiResponse2.success(User())

    @PostMapping("/save-user-Settings")
    fun saveUserSettings(@RequestBody user: String?): ApiResponse2<User> {
        return ApiResponse2.success(User())
    }*/
}