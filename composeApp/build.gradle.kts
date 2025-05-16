import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.reload.ComposeHotRun
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    kotlin("plugin.serialization") version "1.9.10"
}
tasks.withType<ComposeHotRun>().configureEach {
    mainClass.set("empire.digiprem.MainKt")
}
apply(from = "buildSrc/generateMVVIPatternOf.gradle.kts")
apply(from = "buildSrc/configkoinInjectionOf.gradle.kts")
apply(from = "buildSrc/configAppNavigation.gradle.kts")

tasks.register("configDashBoard") {
    val componentName = project.findProperty("componentName")?.toString() ?: "Test"
    val includeInDashboard = (project.findProperty("includedInDsh")?.toString())?.toBoolean() ?: false
    val sectionName = project.findProperty("sectionName")?.toString() ?: ""

    val basePath = projectDir.resolve("src/commonMain/kotlin/empire/digiprem/")
    val dashboardPath = basePath.resolve("navigation")
    val dashboardFile = dashboardPath.resolve("DashboardConfig.kt")
    var dashBoardFileContent = dashboardFile.readText()

    val createNavigationItemTag = "/* Auto config NavigationItem Content*/"
    val configRoutesTag = "/*Auto generate EnumView content*/"
    val createComponentTag = "/* Auto create component */"
    val addImportTag = "/* Auto import file */"
    val createSubNavigationItemTag:(String)->String={ "/* Auto config $it section subNavigationItems Content*/" }


    doLast {

        val subTag=createSubNavigationItemTag( "View${sectionName}")
        fun generateDashboard() {
            if (!dashBoardFileContent.contains(createComponentTag)) {
                dashBoardFileContent = """
            $createComponentTag
            package empire.digiprem.navigation
            
            $addImportTag
            import androidx.compose.foundation.ScrollState
            import androidx.compose.runtime.*
            import androidx.compose.ui.Modifier
            import empire.digiprem.core.utils.getName
            import androidx.compose.material.icons.Icons
            import androidx.compose.material.icons.filled.Edit
            import androidx.navigation.NavHostController
            import empire.digiprem.presentation.components.NavigationItem
            import empire.digiprem.presentation.components.NavigationRailWithPopupDrawer


            @Composable
            fun AppNavigationConfig(
                modifier: Modifier = Modifier,
                navController: NavHostController,
                appScrollState: ScrollState,
            ) {
                var enableNavRail by remember { mutableStateOf(false) }
                var selectedNavItem by remember { mutableStateOf(EnumView.ViewStatistics) }
                
                val navigationItems = listOf<NavigationItem>(
                    $createNavigationItemTag
                )
              
                NavigationRailWithPopupDrawer(
                    enableNavRail = enableNavRail,
                    enabledExpensiveMenu = true,
                    isPopupOpen = true,
                    topBar = {},
                    navigationItems = navigationItems
                ) {
                     AppNavigation(
                            navController = navController,
                        startDestination =
                     ) { enumView->
                      selectedNavItem = enumView
                      enableNavRail =EnumView.entries.filter { it==enumView }.any { it.includeInDashboard }
                    }
                }
            }
            
            
            
                /*Auto generate EnumView*/  
            enum class EnumView(val includeInDashboard:Boolean=false) {
            
                $configRoutesTag
                
            }
                
        """.trimIndent()
            }

        }
        fun dashboardRootConfiguration(componentName: String) {
            /*if (dashBoardFileContent.contains(addImportTag)) {
                val import = "import empire.digiprem.navigation.${componentName}"
                if (!dashBoardFileContent.contains(import)) {
                    dashBoardFileContent = dashBoardFileContent.replace(
                        addImportTag, "$addImportTag \n $import"
                    )
                }
            }*/
            if (dashBoardFileContent.contains(configRoutesTag)) {
                val route = "${componentName}${if(includeInDashboard)"(true)" else ""},"
                //if (!dashBoardFileContent.contains(route)) {
                dashBoardFileContent = dashBoardFileContent.replace(
                    configRoutesTag,
                    """$configRoutesTag 
                        $route""".trimMargin()
                )
                //  }
            }
        }
        fun generateNavigationItem(componentName: String) {
            if (dashBoardFileContent.contains(createNavigationItemTag)) {
                val navItem = """
                NavigationItem(
                    label = ${componentName}.getName(),
                    icon = Icons.Default.Edit,
                    selected =selectedNavItem==EnumView.${componentName},
                    onClick = {
                       navController.navigate(${componentName}())
                     },
                     subNavigationItem = listOf(
                        ${createSubNavigationItemTag(componentName)}
                     )
                  ),
            """.trimIndent()
                if (!dashBoardFileContent.contains(navItem)) {
                    dashBoardFileContent = dashBoardFileContent.replace(
                        createNavigationItemTag, """
                            $createNavigationItemTag
                            $navItem""".trimMargin()
                    )
                }
                dashboardRootConfiguration(componentName)
            }
        }
        fun generateSubNavigationItem(componentName: String) {
            if (dashBoardFileContent.contains(subTag)) {
                if (subTag.contains("$sectionName")) {
                    val navItem = """
                NavigationItem(
                    label = ${componentName}.getName()?:"",
                    icon = Icons.Default.Edit,
                    selected =selectedNavItem==EnumView.${componentName},
                    onClick = {
                       navController.navigate(${componentName}())
                     },
                  ),""".trimIndent()
                    if (!dashBoardFileContent.contains(navItem)) {
                        dashBoardFileContent = dashBoardFileContent.replace(
                            subTag, """
                            $subTag
                            $navItem""".trimMargin()
                        )
                        // dashboardFile.writeText(addNavItem)
                    } else {
                        throw RuntimeException("impossible d'ajouter un sub navItem dans la section $sectionName")
                    }

                    dashboardRootConfiguration(componentName)

                } else {
                     throw RuntimeException("la section=$sectionName du dasboard n'existe pas")
                }
            }
        }

        var componentClassName = "View${componentName}"


        generateDashboard()
        if (dashBoardFileContent.contains(createComponentTag)) {
            if (includeInDashboard) {
                if (sectionName.isEmpty()) {
                    generateNavigationItem(componentClassName)
                } else {
                    generateSubNavigationItem(componentClassName)
                }
            }
        }
        /*else {
            generateNavigationItem(componentClassName)
            //  dashboardRootConfiguration(componentClassName)
        }*/

        println("➡️ Génération du composant : $componentName")
        println("➡️ Inclusion dans dashboard : $includeInDashboard")
        println("➡️ Section cible : $sectionName")
        println("➡️ dashBoardFileContent.contains(createSubNavigationItemTag) : ${dashBoardFileContent.contains(subTag)} ;  subTag=$subTag")
        dashboardFile.writeText(dashBoardFileContent)

    }

}


composeCompiler {
    featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups)
}


kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }

    js(IR) { // Declare JS source-sets
        browser()
        binaries.executable()
    }
    sourceSets {
        val desktopMain by getting
        val jsMain by getting
        val wasmJsMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.android)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.0-alpha17")
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
            implementation(projects.shared)
            implementation(libs.material3.windowSize)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1") // ou plus récent
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
            implementation(libs.koin.core)
            implementation("io.insert-koin:koin-compose-viewmodel:4.0.4")
            implementation("io.insert-koin:koin-compose:4.0.4")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
            // implementation("io.insert-koin:koin-androidx-compose:4.0.4")
        }
        desktopMain.dependencies {
            // https://mvnrepository.com/artifact/org.jetbrains.compose.web/web-core
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.java)
            runtimeOnly("org.jetbrains.compose.hot-reload:core:1.0.0-alpha08")
            implementation("io.insert-koin:koin-core-jvm:4.0.4")
        }

        jsMain.dependencies {
            // Import libraries
            implementation(compose.html.core)
            implementation(compose.runtime)
        }
        wasmJsMain.dependencies {
            //  implementation("org.jetbrains.compose.web:web-core:1.8.0-beta02")
            //implementation("io.insert-koin:koin-core-wasm-js:4.0.4")
        }
    }
}

android {
    namespace = "empire.digiprem"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "empire.digiprem"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.material3.android)
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "empire.digiprem.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "empire.digiprem"
            packageVersion = "1.0.0"
        }
    }
}

compose.resources {
    publicResClass = true // Rendre la classe publique
    packageOfResClass = "composeApp.src.commonMain.ComposeResources.drawable" // Définir un package spécifique
    generateResClass = auto // auto, always, ou none
}
