import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  id("com.github.johnrengelman.shadow") version "7.1.2" apply false
}
apply {
  from(file("gradle/heroku/clean.gradle"))
}
