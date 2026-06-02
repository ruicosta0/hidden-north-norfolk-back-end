import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") // root settings.gradle, then root build.gradle then version from libs.versions.toml
    kotlin("plugin.spring") //kotlin("jvm") and "plugin.spring" is special syntax as kotlin is deeply integrated with Gradle
    id("io.spring.dependency-management") //normal declaration for third party plugin
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${libraries.findVersion("spring-boot").get()}")
    }
}

configure<KotlinJvmProjectExtension> {
    jvmToolchain(21)
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
        jvmTarget = JvmTarget.JVM_21
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}