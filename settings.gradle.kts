pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven ("https://jitpack.io")
        jcenter(){
            content {
               includeModule("com.theartofdev.edmodo", "android-image-cropper")
            }
        }
    }
}

rootProject.name = "NewLook"
include(":app")
 