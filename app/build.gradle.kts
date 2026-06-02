import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("sharrington.spring-boot-app")
}

group = "com.riprojects"
version = "0.0.1-SNAPSHOT"
description = "Sharrington Backend"

tasks {
    named<BootJar>(name="bootJar") {
        from(project(":notification").projectDir.resolve("src/main/resources")) {
            into("")
        }
        from(project(":village").projectDir.resolve("src/main/resources")) {
            into("")
        }
    }
}

//include all service modules into app module to wire together & access functions/classes within them
dependencies {
    implementation(projects.common)
    implementation(projects.village)
    implementation(projects.notification)

    implementation(libs.spring.boot.starter.security)

    implementation(libs.spring.boot.starter.data.jpa)
    runtimeOnly(libs.postgresql)
}