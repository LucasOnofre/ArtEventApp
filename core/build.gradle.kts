plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.onoffrice.core"

    defaultConfig {
        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = ComposeVersions.compose_compiler_version
    }
}

dependencies {
    api(project(":domain"))

    //Koin
    implementation(DependencyInjectionDependencies.koin)

    //Compose
    implementation(ComposeDependencies.compose)
    implementation(ComposeDependencies.compose_material)
    implementation(ComposeDependencies.compose_foundation)
}
