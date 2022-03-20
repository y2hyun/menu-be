plugins {
  id("org.springframework.boot")
  kotlin("plugin.jpa")
  kotlin("plugin.allopen")
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

tasks.bootJar {
  enabled = false
}