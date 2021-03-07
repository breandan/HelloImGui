import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  application
  kotlin("jvm") version "1.4.21"
}

group = "me.breandan"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  maven("https://dl.bintray.com/kotlin/kotlin-dev")
  maven("https://oss.sonatype.org/content/repositories/snapshots/")
  maven("https://jitpack.io")
}

application.mainClass.set("HelloImGuiKt")

dependencies {
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//  compile("com.github.kotlin-graphics:imgui:-SNAPSHOT")
  implementation("com.github.kotlin-graphics.imgui:bgfx:-SNAPSHOT")
  implementation(platform("org.lwjgl:lwjgl-bom:${findProperty("lwjglVersion")}"))

  val lwjglNatives = when (OperatingSystem.current()) {
    OperatingSystem.LINUX -> "natives-linux"
    else -> ""
  }

  // Look up which modules and versions of LWJGL are required and add setup the approriate natives.
  configurations["compile"].resolvedConfiguration.resolvedArtifacts.forEach {
    if (it.moduleVersion.id.group == "org.lwjgl") {
      "runtime"("org.lwjgl:${it.moduleVersion.id.name}:${it.moduleVersion.id.version}:$lwjglNatives")
    }
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}