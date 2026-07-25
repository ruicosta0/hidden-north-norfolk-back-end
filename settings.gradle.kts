pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "HiddenNorthNorfolkBackend"

include("app")
include("village")
include("notification")
include("common")