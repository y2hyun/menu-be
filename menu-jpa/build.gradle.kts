plugins {
  id("org.springframework.boot") version "2.6.4"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
  kotlin("jvm") version "1.6.10"
  kotlin("plugin.spring") version "1.6.10"
  kotlin("plugin.jpa") version "1.6.10"
  kotlin("plugin.allopen") version "1.6.10"
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