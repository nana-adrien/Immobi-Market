import java.io.File


tasks.register("generateMVVIPatternOf") {

    /*
    * Récupère le paramètre -Pscreen=Nom passé en ligne de commande :

    findProperty("screen") récupère la valeur transmise via -P.

    .capitalize() met la première lettre en majuscule (home → Home).

    Si aucun paramètre n’est passé, la valeur par défaut est "Home".
    * */
    val screenName = project.findProperty("view")?.toString()?.capitalize() ?: run {
        print("Veuillez entrer le nom de l'écran : ")
        System.console()?.readLine() ?: "CreateAnnonce"
    }
    val includeInDashboard = (project.findProperty("includedInDsh")?.toString())?.toBoolean() ?: true
    val sectionName = project.findProperty("sectionName")?.toString() ?: "MyAnnonces"


    /*Définit le chemin où les fichiers seront créés, en minuscule (Home → home).*/

    val baseDir = file("src/commonMain/kotlin/empire/digiprem/")

    /*⚙️ Indique que ce qui suit doit être exécuté à la fin de la tâche Gradle.*/
    doLast {
        fun writeIfNotExists(file: File, content: String) {
            if (!file.exists()) {
                file.writeText(content)
                println("Fichier ${file.name} créé avec succès.")
            } else {
                println("Le fichier ${file.name} existe déjà.")
            }
        }
        // Créer tous les dossiers nécessaires s'ils n'existent pas déjà
        val directoriesToCreate = listOf(
            "presentation/views",
            "presentation/models",
            "presentation/intents",
            "presentation/viewmodels",
            "presentation/viewmodels/base"
        )

        directoriesToCreate.forEach { subDir ->
            val dir = File(baseDir, subDir)
            if (!dir.exists()) {
                dir.mkdirs()  // Crée le répertoire et ses sous-répertoires si nécessaire
                println("Répertoire créé : $dir")
            }
        }

        var viewClassName = screenName.replaceFirstChar { it.uppercaseChar() }

        fun verifySectionExist():Boolean{
            val sectionFile = File(baseDir, "presentation/views/${sectionName}View.kt")
            if (sectionFile.exists()) {
                viewClassName="${sectionName.replaceFirstChar { it.uppercaseChar() }}$viewClassName"
                return true
            }else{
                return false
            }
        }
        if (sectionName.isEmpty() ||verifySectionExist()) {
            /* 📂 Crée tous les dossiers nécessaires s'ils n'existent pas déjà (mkdir -p en shell).*/
            baseDir.mkdirs()
            /*Définit les fichiers à créer :

            HomeScreen.kt

            HomeState.kt

            HomeViewModel.kt"*/

            val viewFile = File(baseDir, "presentation/views/${viewClassName}View.kt")
            val modelFile = File(baseDir, "presentation/models/${viewClassName}Model.kt")
            val intentFile = File(baseDir, "presentation/intents/${viewClassName}Intent.kt")
            val viewModelFile = File(baseDir, "presentation/viewmodels/${viewClassName}ViewModel.kt")
            val abstractViewModelFile = File(baseDir, "presentation/viewmodels/base/AbstractViewModel.kt")


            /*💡 Écriture du contenu de chaque fichier*/
            writeIfNotExists(
                abstractViewModelFile,
                """
            package empire.digiprem.presentation.viewmodels.base
            
            import androidx.lifecycle.ViewModel
            import kotlinx.coroutines.flow.MutableStateFlow
            import kotlinx.coroutines.flow.asStateFlow
            
            abstract class AbstractViewModel<model,intent>(private val defaultState:model):ViewModel() {
            
                protected val _mutableState = MutableStateFlow(defaultState)
                val state=_mutableState.asStateFlow()
            
              abstract  fun onIntentHandler(intent:intent)
            }
            """.trimIndent()
            )
            /*💡 Écriture du contenu de chaque fichier*/
            writeIfNotExists(
                viewFile,
                """
            package empire.digiprem.presentation.views
           
              import empire.digiprem.navigation.View${viewClassName}
             import androidx.compose.runtime.Composable
             import androidx.compose.runtime.collectAsState
             import androidx.lifecycle.viewmodel.compose.viewModel
             import androidx.compose.runtime.getValue
             import org.koin.compose.viewmodel.koinViewModel
             import androidx.navigation.NavHostController
            import empire.digiprem.presentation.viewmodels.${viewClassName}ViewModel
           import empire.digiprem.presentation.intents.${viewClassName}Intent
                   
            @Composable
            fun ${viewClassName}View(
            view${viewClassName}:View${viewClassName},
            navController: NavHostController,
            ${screenName.toLowerCase()}ViewModel:${viewClassName}ViewModel = koinViewModel()
            ) {
                   // val ${screenName.toLowerCase()}ViewModel:${viewClassName}ViewModel = viewModel{${viewClassName}ViewModel()}
                    val state by ${screenName.toLowerCase()}ViewModel.state.collectAsState()
                    val onSendIntent=${screenName.toLowerCase()}ViewModel::onIntentHandler
            }
            """.trimIndent()
            )
            writeIfNotExists(
                modelFile,
                """
        package empire.digiprem.presentation.models
        
        data class ${viewClassName}Model(
            val isLoading: Boolean = false
        )
        """.trimIndent()
            )

            writeIfNotExists(
                intentFile,
                """
        package empire.digiprem.presentation.intents
        
        sealed class ${viewClassName}Intent {
        
         }
        """.trimIndent()
            )

            writeIfNotExists(
                viewModelFile,
                """
            package empire.digiprem.presentation.viewmodels
            import empire.digiprem.presentation.viewmodels.base.AbstractViewModel
            import empire.digiprem.presentation.intents.${viewClassName}Intent
            import empire.digiprem.presentation.models.${viewClassName}Model
            class ${viewClassName}ViewModel : AbstractViewModel<${viewClassName}Model,${viewClassName}Intent>(defaultState = ${viewClassName}Model()) {
                // TODO: ViewModel logic
            
                    override fun onIntentHandler(intent: ${viewClassName}Intent) {
        
                     }
            }
                """.trimIndent()
            )
            exec {
                workingDir = rootDir
                commandLine = listOf(
                    "cmd", "/c",
                    "gradlew.bat",
                    "configkoinInjectionOf",
                    "-PinjectClass=${viewClassName}ViewModel"
                )
            }
            exec {
                workingDir = rootDir
                commandLine = listOf(
                    "cmd", "/c",
                    "gradlew.bat",
                    "configAppNavigation",
                    "-PcomposableName=${viewClassName}"
                )
            }
            exec {
                workingDir = rootDir
                commandLine = listOf(
                    "cmd", "/c",
                    "gradlew.bat",
                    "configDashBoard",
                    "-PcomponentName=${viewClassName}",
                    "-PincludedInDsh=${includeInDashboard}",
                    "-PsectionName=${sectionName}"
                )
            }

        }
        else {
            println("✅ aucune section conrrespondant a ${sectionName}View")
        }
        println("✅ Fichiers générés pour l'écran $screenName")
    }
}
