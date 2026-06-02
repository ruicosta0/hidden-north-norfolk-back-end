plugins {
    id("java-library") // this is a library module that provides functionality to other library modules
    id("sharrington.spring-boot-service")
    kotlin("plugin.jpa")
}

group = "com.riprojects"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.common)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}