plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    //Coroutines
    implementation(AsyncDependencies.coroutines)
    implementation(AsyncDependencies.coroutines_android)

    //Koin
    implementation(DependencyInjectionDependencies.koin_core)


    //Testing
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.coroutines_test)

    //paging 3
    implementation(SupportDependencies.pagingCommonKtx)
}
