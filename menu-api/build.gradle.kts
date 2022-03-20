plugins {
  id("org.springframework.boot")
  id("org.openapi.generator") version "5.4.0"
  id("com.github.johnrengelman.shadow")
}

dependencies {
  implementation(project(":menu-jpa"))
  implementation(project(":jwtauth"))
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
/*
tasks {
  named<ShadowJar>("shadowJar") {
    mergeServiceFiles()
    manifest {
      attributes(mapOf("Main-Class" to "com.yang.menu.menuapi.MenuApiApplicationKt"))
    }
  }
}

apply {
  from(file("${rootProject.projectDir}/gradle/heroku/clean.gradle"))
  from(file("${rootProject.projectDir}/gradle/heroku/stage.gradle"))
}

tasks.shadowJar {
  archiveClassifier.set("")
}
 */