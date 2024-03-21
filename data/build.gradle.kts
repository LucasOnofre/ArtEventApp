plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.onoffrice.data"

    defaultConfig {
        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        all {
            buildConfigField("String", "BASE_URL", "\"https://api.artic.edu/api/v1/\"")
        }
    }
}

dependencies {
    implementation(project(":domain"))

    //Network
    implementation(NetworkDependencies.retrofit)
    implementation(NetworkDependencies.okhttp)
    implementation(NetworkDependencies.gson)
    implementation(NetworkDependencies.gson_converter)

    //Koin
    implementation(DependencyInjectionDependencies.koin)
    implementation(DependencyInjectionDependencies.koin_core)

    //Room
    implementation(SupportDependencies.roomCommon)
    implementation(SupportDependencies.roomKtx)
    implementation(SupportDependencies.roomRuntime)
    kapt(SupportDependencies.roomCompiler)

    //Testing
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutines_test)

    //paging 3
    implementation(SupportDependencies.pagingRuntimeKtx)
    implementation(SupportDependencies.pagingCompose)

}
