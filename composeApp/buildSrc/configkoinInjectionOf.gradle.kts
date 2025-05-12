tasks.register("configkoinInjectionOf") {

    val injectClassName = project.findProperty("injectClass")?.toString() ?: "TestViewModel"


    val filePath = "src/commonMain/kotlin/empire/digiprem/core/di/InitializeKoin.kt"
    val file = file(filePath)

    doLast {
        if (!file.exists()) {
            throw GradleException("❌ Le fichier $filePath n'existe pas.")
        }
        val content = file.readText()

        val newModuleLine = " viewModel { ${injectClassName}() }"
        val importviewModel = "import org.koin.core.module.dsl.viewModel"
        val importFile = "import empire.digiprem.presentation.viewmodels.${injectClassName}"

        if (content.contains(newModuleLine)) {
            println("✅ Le module $injectClassName est déjà inclus.")
        } else {
            val updatedContent = content.replace(
                "val commonModules = module {",
                """
                  ${if (content.contains(importviewModel)) { "" } else { importviewModel }}
                   ${if (content.contains(importFile)) { "" } else { importFile }}
                   
                   
                    val commonModules = module {
                    $newModuleLine
                    """.trimIndent()
            )
            file.writeText(updatedContent)
            println("✅ Module $injectClassName ajouté à commonModules.$updatedContent")
        }
    }
}


