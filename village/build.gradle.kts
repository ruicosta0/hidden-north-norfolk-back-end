plugins {
    //id("java-library") // this is a library module that provides functionality to other library modules
    id("hiddennorthnorfolk.spring-boot-service")
    alias(libs.plugins.kotlin.jpa)
}

group = "com.riprojects"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.common)
    implementation(libs.spring.boot.starter.security)

    //implementation("org.apache.httpcomponents.client5:httpclient5")
    //implementation("com.squareup.okhttp3:okhttp:4.12.0")
    //implementation("org.springframework.http.client:okhttp3-client-http-request-factory")

    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation(libs.spring.boot.starter.data.jpa)
    runtimeOnly(libs.postgresql)

   // implementation(libs.jakarta.)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}