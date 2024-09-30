import org.openapitools.generator.gradle.plugin.tasks.ValidateTask

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("kapt") version "2.0.20"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "1.9.25"

    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.openapi.generator") version "7.8.0"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        group = "com.marukhan"
        version = "0.0.1"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.mapstruct:mapstruct:1.6.2")

    kapt("org.mapstruct:mapstruct-processor:1.6.2")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

//openapi
kotlin.sourceSets["main"].kotlin.srcDir("${layout.buildDirectory.get()}/openapi-generated/src/main/kotlin")

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
tasks.register<ValidateTask>("validateSpec") {
    outputs.upToDateWhen { false }
    outputs.cacheIf { false }
    inputSpec.set("${rootDir}/src/main/resources/openapi/organization-openapi-v1.0.yml")
//    recommend.set(true)
}

// Generating code by a single specification
openApiGenerate {
    id.set("request")
    generatorName.set("kotlin-spring")
    inputSpec.set("${rootDir}/src/main/resources/openapi/organization-openapi-v1.0.yml")
    outputDir.set("${layout.buildDirectory.get()}/openapi-generated/src/main/kotlin")
    apiPackage.set("com.marukhan.openapi.api.request")
    modelPackage.set("com.marukhan.openapi.model.request")
    configOptions.set(openApiConfig)
    typeMappings.set(openApiTypeMapping)
    importMappings.set(openApiImportMapping)
}