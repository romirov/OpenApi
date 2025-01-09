import org.openapitools.generator.gradle.plugin.tasks.ValidateTask

plugins {
		kotlin("jvm") version "1.9.25"
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
		//implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("jakarta.validation:jakarta.validation-api")

		runtimeOnly("org.postgresql:postgresql")

		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
		testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
		testImplementation("org.testcontainers:postgresql:1.20.4")
		testImplementation("org.testcontainers:junit-jupiter:1.20.3")
		testImplementation("io.mockk:mockk:1.13.13")
		testImplementation("com.ninja-squad:springmockk:4.0.2")
		testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
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
}

// Generating code by a single specification
openApiGenerate {
		id.set("request")
		generatorName.set("kotlin-spring")
		inputSpec.set("${rootDir}/src/main/resources/openapi/organization-openapi-v1.0.yml")
		outputDir.set("${layout.buildDirectory.get()}/openapi-generated")
		apiPackage.set("com.marukhan.openapi.api.request")
		modelPackage.set("com.marukhan.openapi.model.request")
		configOptions.set(openApiConfig)
		typeMappings.set(openApiTypeMapping)
		importMappings.set(openApiImportMapping)
}

tasks.compileKotlin {
		dependsOn(tasks.openApiGenerate)
}