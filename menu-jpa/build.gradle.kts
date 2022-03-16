plugins {
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  id("org.jlleitschuh.gradle.ktlint")
  kotlin("jvm")
  kotlin("plugin.spring")
  kotlin("plugin.jpa")
  kotlin("plugin.allopen")
}

group = "com.yang.menu"
version = "0.1"

repositories {
  mavenCentral()
}

dependencies {
  api("org.springframework.boot:spring-boot-starter")
  api("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  // runtimeOnly("mysql:mysql-connector-java")
  runtimeOnly("org.postgresql:postgresql:42.3.3")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

allOpen {
  annotations(
    listOf(
      "javax.persistence.Entity",
      "javax.persistence.MappedSuperclass",
      "javax.persistence.Embeddable"
    )
  )
}

configurations {
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.bootJar {
  enabled = false
}