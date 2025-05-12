tasks.register("configAppNavigation") {
    val composableName = project.findProperty("composableName")?.toString() ?: "Main"
    val basicPatch = file("src/commonMain/kotlin/empire/digiprem/navigation/")
    var navFile = file(basicPatch.path + "AppNavigation.kt")
    var navObjectFile = file(basicPatch.path + "AppNavigationObjects.kt")
    if (!navFile.exists()) {
        navFile = File(basicPatch, "AppNavigation.kt")
    }
    if (!navObjectFile.exists()) {
        navObjectFile = File(basicPatch, "AppNavigationObjects.kt")
    }
    doLast {
        val navFileContent = navFile.readText()
        val navObjectFileContent = navObjectFile.readText()
        val autoImportFileTag = "/*@Auto import file*/"
        val autoCreateComposableTag = "/*@Auto Generate Composable*/"

        val addClass =
            """
            @Serializable
            data class View${composableName}(val scrollPosition: Int = 0)
        """.trimIndent()



        if (!navObjectFileContent.contains(autoImportFileTag)) {
            navObjectFile.appendText("\n \n")
            navObjectFile.writeText(
                """
              package empire.digiprem.navigation
              $autoImportFileTag
              import kotlinx.serialization.Serializable
              
              $autoCreateComposableTag
              $addClass
                """.trimIndent()
            )
        } else if (!navObjectFileContent.contains(addClass)) {
            val updatedContent = navObjectFileContent.replace(
                "$autoCreateComposableTag",
                """
                $autoCreateComposableTag
                $addClass
            """.trimIndent()
            )
            navObjectFile.writeText(updatedContent)
        } else {
            println("{ $addClass }est deja integer dans le navHost ")
        }
        val addComponent = ""+
        "composable<View${composableName}>{\n"+
            "onNavigate(View${composableName}.getName())\n"+
           " val view${composableName} = it.toRoute<View${composableName}>()\n"+
            "${composableName}View(view${composableName}=view${composableName},navController=navController,)\n"+
        " }"

        val importView = """
        import empire.digiprem.presentation.views.${composableName}View
        import empire.digiprem.navigation.View${composableName}
        """.trimIndent()
        val InitializeNavHost = """
    package empire.digiprem.navigation

    $autoImportFileTag
     import androidx.compose.runtime.Composable
     import androidx.compose.ui.Modifier
     import androidx.navigation.NavHostController
     import androidx.navigation.compose.NavHost
     import androidx.navigation.compose.composable
     import androidx.navigation.toRoute
     import empire.digiprem.core.utils.getName
     import kotlin.reflect.KClass
     $importView
     
     /*@Auto Generate*/ 
        @Composable
        fun AppNavigation(
            modifier: Modifier=Modifier,
            navController: NavHostController,
            startDestination: KClass<*>,
            onNavigate: (String?) -> Unit
        ) {
            NavHost(
                navController,
                modifier = modifier,
                startDestination = startDestination,
            ) {
            $autoCreateComposableTag
             $addComponent
            }
         }""".trimIndent()
        if (!navFileContent.contains(autoImportFileTag)) {
            navFile.appendText("\n \n")
            navFile.writeText(InitializeNavHost)
            println("✅ Navhost creer.$InitializeNavHost")
        } else {
            if (!navFileContent.replace(Regex("\\s+"), "").contains("${addComponent.replace(Regex("\\s+"), "")}")) {
                val updatedContent = navFileContent.replace(
                    "$autoImportFileTag",
                    """
                     $autoImportFileTag
                     ${
                        if (!navFileContent.contains(importView)) {
                            importView
                        } else {
                            ""
                        }
                    }
                    """.trimIndent()
                ).replace(
                    "$autoCreateComposableTag", """                  
                    $autoCreateComposableTag
                    $addComponent
                    """.trimIndent()
                )
                navFile.writeText(updatedContent)
                println("✅ composant  ajouté au navHost.$updatedContent")
            } else {
                println("{ $addComponent }est deja integer dans le navHost ")
            }

        }
    }
}