package empire.digiprem.services

import empire.digiprem.models.Users
import empire.digiprem.repositories.UsersRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.*

@Transactional
@Service
class UserProfileService(var usersRepository: UsersRepository ) {


    fun updateProfile(identity: String, newPhone: String) {
        val user = Users(
            name = newPhone,
                    firstName = newPhone
        )
        usersRepository.save(user)
    }

    @Throws(IOException::class)
    fun updateProfilePicture(identity: String?, file: MultipartFile) {
        // Users user = loadUserByIdentity(identity);
        val uploadDir = "uploads/profile-photos/"
        val fileName = UUID.randomUUID().toString() + "-" + file.originalFilename

        val uploadDirPath = Paths.get(uploadDir)
        if (!Files.exists(uploadDirPath)) {
            Files.createDirectories(uploadDirPath)
        }
        val filePath = uploadDirPath.resolve(fileName)
        Files.copy(file.inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)
        val fileUrl = "http://localhost:8080/uploads/profile-photos/$fileName"
        // user.setProfileUrl(fileUrl);
    }
}