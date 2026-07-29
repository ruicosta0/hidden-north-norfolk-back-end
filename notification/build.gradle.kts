plugins {
    id("java-library") // this is a library module that provides functionality to other library modules
    id("hiddennorthnorfolk.spring-boot-service")
    //kotlin("jvm")
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