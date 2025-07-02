package empire.digiprem.service.database

import empire.digiprem.dto.account.CreateAccountRequestDTO
import empire.digiprem.enums.FileUriTypeEnum
import empire.digiprem.model.AppFile
import empire.digiprem.models.database.UserProfile
import empire.digiprem.models.database.User
import empire.digiprem.repositories.database.IUserProfileRepository
import empire.digiprem.services.file.FilesService
import empire.digiprem.utils.tools
import org.springframework.stereotype.Service
import kotlin.uuid.ExperimentalUuidApi

@Service
class IProfilServiceImpl(
    val profileRepository: IUserProfileRepository,
    val utilisateurService: UtilisateurService,
    private val filesService: FilesService
)  {
     @OptIn(ExperimentalUuidApi::class)
     fun createUserProfile(user: User, requestProfile:CreateAccountRequestDTO,) {
         if(profileRepository.existsById(user.id)){
             throw IllegalArgumentException("Profile already exists")
         }
         val appFile= AppFile(
             byteArray = tools.createAvatarImage(requestProfile.lastName) ,//file.bytes,
             mimeType = FileUriTypeEnum.IMAGE_JPEG ,
             mimeTypeDescriptor ="image/jpeg",
             name =user.id.toString()  ,
         )
         val userProfile=UserProfile(
             user_id=user.id,
             urlProfil =filesService.uploadProfile(user.email,appFile) ,
             user=user,
             nom = requestProfile.lastName,
             prenom = requestProfile.firstName,
             genre=requestProfile.gender,
             //authentificationDeuxFacteursActivee = requestProfile.enabledTwoFactorAuth,
         )
        profileRepository.save(userProfile)
    }

     fun UpdateUserProfile(profile: UserProfile) {
         profileRepository.save(profile)
    }

     fun changeUserProfileImage(user: User, profileUrl: String) {
        /* if (utilisateurService.loadUserByUsername(user)){

         }*/

         ///profileRepository.save(profil)
    }


}