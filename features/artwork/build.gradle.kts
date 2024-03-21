plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.onoffrice.artwork"

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
    implementation(project(":core"))

    //Support
    implementation(SupportDependencies.core)
    implementation(SupportDependencies.navigation)

    //Compose
    implementation(ComposeDependencies.compose_activity)
    implementation(ComposeDependencies.compose_material)
    implementation(ComposeDependencies.compose_accompanist)
    implementation(ComposeDependencies.composeRuntime)

    //Koin
    implementation(DependencyInjectionDependencies.koin_compose)

    //Coroutines
    implementation(AsyncDependencies.coroutines)
    implementation(AsyncDependencies.coroutines_android)

    //Testing
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.arch)
    testImplementation(TestDependencies.turbine)
    testImplementation(TestDependencies.mockito)
    androidTestImplementation(TestDependencies.jUnitCompose)
    androidTestImplementation(TestDependencies.testManifestCompose)
    androidTestImplementation(TestDependencies.koin_test)
    androidTestImplementation(TestDependencies.koin_test_junit)
    androidTestImplementation(TestDependencies.mockito_android)
    androidTestImplementation(TestDependencies.nav_test)

    //paging 3
    implementation (SupportDependencies.pagingRuntimeKtx)
    implementation (SupportDependencies.pagingCompose)
    implementation (ComposeDependencies.coilCompose)

}
