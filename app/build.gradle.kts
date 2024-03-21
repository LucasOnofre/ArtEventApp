import java.util.Locale

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.onoffrice"

    defaultConfig {
        applicationId = Releases.applicationId
        versionCode = Releases.versionCode
        versionName = Releases.versionName

        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val name = parent?.name?.capitalize(Locale.getDefault())
        setProperty("archivesBaseName", "$name-$versionName")
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":features:artwork"))

    implementation(DependencyInjectionDependencies.koin)
    implementation(DependencyInjectionDependencies.koin_core)
}
