import org.openapitools.generator.gradle.plugin.tasks.GenerateTask
import org.openapitools.generator.gradle.plugin.tasks.ValidateTask

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
//    kotlin("plugin.jpa") version "1.9.25"
    id("org.openapi.generator") version "7.8.0"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        group = "com.example"
        version = "0.0.1"
    }
}

repositories {
    mavenCentral()
}

dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin.sourceSets["main"].kotlin.srcDir("${layout.buildDirectory.get()}/openapi-generated/src/main/kotlin")

//openapi
val openApiConfig = mapOf(
    "serializationLibrary" to "jackson",
        "serializableModel" to "true",
        "interfaceOnly" to "true",
        "skipDefaultInterface" to "true",
        "useTags" to "true",
        "enumPropertyNaming" to "UPPERCASE",
        "dateLibrary" to "java8",
        "documentationProvider" to "source",
        "useBeanValidation" to "true",
        "performBeanValidation" to "true",
        "library" to "spring-boot",
        "exceptionHandler" to "false",
        "supportFileToGenerate" to "ApiUtil",
        "useSpringBoot3" to "true",
        "useJakartaEe" to "true"
)

val openApiTypeMapping = mapOf(
    "java.time.OffsetDateTime" to "java.time.Instant",
        "kotlin.Double" to "java.math.BigDecimal",
        "kotlin.Float" to "java.math.BigDecimal"
)

val openApiImportMapping = mapOf(
        "java.time.OffsetDateTime" to "java.time.Instant",
)

// Validating a single specification
tasks.register <ValidateTask>( "validateSpec") {
    outputs.upToDateWhen { false }
    outputs.cacheIf { false }

    inputSpec.set("$rootDir/petstore-v3.0-invalid.yaml")

    recommend.set(true)
}

tasks.register <GenerateTask>( "generateCodeBySpec") {
    id.set("request")
    generatorName.set("kotlin-sping")
    inputSpec.set("$rootDir/specs/petstore-v3.0.yaml")
    outputDir.set("$buildDir/generated")
    apiPackage.set("com.example.openapi.api.request")
    configOptions.set(openApiConfig)
    typeMappings.set(openApiTypeMapping)
    importMappings.set(openApiImportMapping)
}

//openApiGenerate {
//    generatorName.set("kotlin")
//    inputSpec.set("$rootDir/specs/petstore-v3.0.yaml")
//    outputDir.set("$buildDir/generated")
//    apiPackage.set("org.openapi.example.api")
//    invokerPackage.set("org.openapi.example.invoker")
//    modelPackage.set("org.openapi.example.model")
//    configOptions.put("dateLibrary", "java8")
//}