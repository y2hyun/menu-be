plugins {
  id("org.springframework.boot")
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  api("org.springframework.security:spring-security-web")
  api("org.springframework.security:spring-security-config")
  implementation("com.auth0:java-jwt:3.19.0")
  implementation("javax.servlet:javax.servlet-api")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.security:spring-security-test")
  testImplementation("io.mockk:mockk:1.12.3")
}

tasks.bootJar {
  enabled = false
}