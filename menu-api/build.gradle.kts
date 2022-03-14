import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.6.4"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  kotlin("jvm") version "1.6.10"
  kotlin("plugin.spring") version "1.6.10"
  id("org.openapi.generator") version "5.4.0"
  id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
  id("com.github.johnrengelman.shadow")
}

group = "com.yang.menu"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(project(":menu-jpa"))
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.register<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("openapiGenerate") {
  generatorName.set("kotlin-spring")
  configFile.set("$rootDir/menu-api/openapi-generator.json")
  inputSpec.set("$rootDir/menu-api/apispecs/menuapi.yaml")
  outputDir.set("$rootDir/menu-api")
  packageName.set("com.yang.menu.menuapi.web")
  invokerPackage.set("${packageName.get()}.api")
  apiPackage.set("${packageName.get()}.api")
  modelPackage.set("${packageName.get()}.payload")
  configOptions.set(
    mapOf(
      "dateLibrary" to "java8"
    )
  )
  globalProperties.set(
    mapOf("modelDocs" to "false")
  )
  finalizedBy(tasks.named("ktlintFormat"))
}

tasks.jar {
  enabled = false
}

tasks {
  named<ShadowJar>("shadowJar") {
    mergeServiceFiles()
    manifest {
      attributes(mapOf("Main-Class" to "com.yang.menu.menuapi.MenuApiApplicationKt"))
    }
  }
}

apply {
  from(file("${rootProject.projectDir}/gradle/heroku/stage.gradle"))
}