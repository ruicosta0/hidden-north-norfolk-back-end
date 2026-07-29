// this is the convention plugin for each services modules (notification, village)

plugins {
    id("org.springframework.boot")
    id("hiddennorthnorfolk.kotlin-common") // this is reference to hiddennorthnorfolk.kotlin-common.gradle.kts to apply its logic
    id("io.spring.dependency-management") // required to be declared again despite being in hiddennorthnorfolk.kotlin-common
}

dependencies {
    "implementation"(libraries.findLibrary("kotlin-reflect").get())
    "implementation"(libraries.findLibrary("kotlin-stdlib").get())
    "implementation"(libraries.findLibrary("spring-boot-starter-web").get())
    "implementation"(libraries.findLibrary("spring-boot-starter-validation").get())


    "testImplementation"(libraries.findLibrary("spring-boot-starter-test").get())
    "testImplementation"(libraries.findLibrary("kotlin-test-junit5").get())
    "testRuntimeOnly"(libraries.findLibrary("junit-platform-launcher").get())
}