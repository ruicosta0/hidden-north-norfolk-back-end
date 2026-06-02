
// note that common is not a service module so don't include sharrington.spring-boot-service
plugins {
    id("java-library") // this is a library module that provides functionality to other library modules
    id("sharrington.kotlin-common")
    kotlin("plugin.jpa")
}

group = "com.riprojects"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    api(libs.jackson.module.kotlin)
    api(libs.kotlin.reflect)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}