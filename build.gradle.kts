import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.github.johnrengelman.shadow") version "7.1.2" apply false
  id("org.springframework.boot") version "2.6.4" apply false
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  id("org.jlleitschuh.gradle.ktlint") version "10.2.1" apply false
  kotlin("jvm") version "1.6.10"
  kotlin("plugin.spring") version "1.6.10" apply false
  kotlin("plugin.jpa") version "1.6.10" apply false
  kotlin("plugin.allopen") version "1.6.10" apply false
}

allprojects {
  apply {
    plugin("java")
    plugin("io.spring.dependency-management")
    plugin("org.jlleitschuh.gradle.ktlint")
    plugin("org.jetbrains.kotlin.jvm")
    plugin("org.jetbrains.kotlin.plugin.spring")
  }
  group = "com.yang.menu"
  version = "0.0.1-SNAPSHOT"
  java.sourceCompatibility = JavaVersion.VERSION_11

  repositories {
    mavenCentral()
  }
}

subprojects {
  configurations {
    compileOnly {
      extendsFrom(configurations.annotationProcessor.get())
    }
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
}