plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "1.9.25"

}

 group = "empire.digiprem"
 version = "1.0.0"
application {
    mainClass.set("empire.digiprem.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }

}
configurations.all {
    resolutionStrategy {
        force("org.apache.commons:commons-compress:1.21") // ou la version attendue par Spring Boot
    }
}



repositories {
    mavenCentral()
}


dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-websocket:3.4.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    //implementation("org.flywaydb:flyway-core:11.9.1")
    implementation("org.liquibase:liquibase-core:4.28.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.googlecode.libphonenumber:libphonenumber:8.13.24")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.0")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.mapstruct:mapstruct:1.6.3")
    implementation("com.twilio.sdk:twilio:8.25.0")
    implementation("com.google.firebase:firebase-admin:9.2.0")
    implementation("com.auth0:java-jwt:4.5.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("io.konform:konform-jvm:0.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
    testImplementation(libs.kotlin.test.junit)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

