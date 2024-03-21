object AndroidDependencies {
    val gradle by lazy { "com.android.tools.build:gradle:${AndroidVersions.gradle}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${AndroidVersions.kotlin}" }
    val dependencyUpdate by lazy { "com.github.ben-manes:gradle-versions-plugin:${AndroidVersions.dependency_updates}" }
    val detekt by lazy { "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${AndroidVersions.detekt}" }
    val ktlint by lazy { "org.jlleitschuh.gradle:ktlint-gradle:${AndroidVersions.ktlint}" }
    val navigationSafeArgs by lazy {
        "androidx.navigation:navigation-safe-args-gradle-plugin:${AndroidVersions.navigation_safe_args}"
    }
    val google by lazy { "com.google.gms:google-services:${AndroidVersions.google}" }
    val firebase by lazy { "com.google.firebase:firebase-crashlytics-gradle:${AndroidVersions.firebase}" }
}

object SupportDependencies {
    val core by lazy { "androidx.core:core-ktx:${SupportVersions.core_version}" }
    val material by lazy { "com.google.android.material:material:${SupportVersions.material_version}" }
    val app_compat by lazy { "androidx.appcompat:appcompat:${SupportVersions.app_compat_version}" }
    val navigation by lazy { "androidx.navigation:navigation-ui-ktx:${SupportVersions.navigation_version}" }
    val splash by lazy { "androidx.core:core-splashscreen:${SupportVersions.splash_version}" }
    val pagingRuntimeKtx by lazy { "androidx.paging:paging-runtime-ktx:${SupportVersions.paging}" }
    val pagingCompose by lazy { "androidx.paging:paging-compose:${SupportVersions.paging_compose}" }
    val pagingCommonKtx by lazy { "androidx.paging:paging-common-ktx:${SupportVersions.paging}" }
    val roomCommon by lazy { "androidx.room:room-common:${SupportVersions.room}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${SupportVersions.room}" }
    val roomRuntime by lazy { "androidx.room:room-runtime:${SupportVersions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${SupportVersions.room}" }
}

object ComposeDependencies {
    val compose by lazy { "androidx.compose.ui:ui:${ComposeVersions.compose_version}" }
    val compose_nav by lazy { "androidx.navigation:navigation-compose:${ComposeVersions.compose_nav_version}" }
    val compose_viewmodel by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${ComposeVersions.compose_viewmodel_version}" }
    val compose_activity by lazy { "androidx.activity:activity-compose:${ComposeVersions.compose_activity_version}" }
    val compose_material by lazy { "androidx.compose.material:material:${ComposeVersions.compose_material_version}" }
    val compose_icons by lazy { "androidx.compose.material:material-icons-extended:${ComposeVersions.compose_icons_version}" }
    val compose_windowsize by lazy { "androidx.compose.material3:material3-window-size-class:${ComposeVersions.compose_windowsize_version}" }
    val compose_foundation by lazy { "androidx.compose.foundation:foundation:${ComposeVersions.compose_foundation_version}" }
    val compose_uitooling by lazy { "androidx.compose.ui:ui-tooling:${ComposeVersions.compose_version}" }
    val compose_toolingpreview by lazy { "androidx.compose.ui:ui-tooling-preview:${ComposeVersions.compose_version}" }
    val compose_accompanist by lazy { "com.google.accompanist:accompanist-navigation-animation:${ComposeVersions.compose_accompanist_version}" }
    val coilCompose by lazy { "io.coil-kt:coil-compose:${ComposeVersions.composeCoil}" }
    val composeRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-compose:${ComposeVersions.compose_runtime}" }

}

object NetworkDependencies {
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${NetworkVersions.retrofit_version}" }
    val okhttp by lazy { "com.squareup.okhttp3:logging-interceptor:${NetworkVersions.okHttp_interceptor_version}" }
    val gson by lazy { "com.google.code.gson:gson:${NetworkVersions.gson_version}" }
    val gson_converter by lazy { "com.squareup.retrofit2:converter-gson:${NetworkVersions.gson_converter_version}" }
    val moshi_kotlin by lazy { "com.squareup.moshi:moshi-kotlin:${NetworkVersions.moshi_kotlin_version}" }
    val moshi_adapter by lazy { "com.squareup.moshi:moshi-adapters:${NetworkVersions.moshi_adapter_version}" }
    val moshi_retrofit by lazy { "com.squareup.retrofit2:retrofit:${NetworkVersions.moshi_retrofit_version}" }
    val moshi_converter by lazy { "com.squareup.retrofit2:converter-moshi:${NetworkVersions.moshi_retrofit_version}" }
}

object DependencyInjectionDependencies {
    val koin by lazy { "io.insert-koin:koin-android:${DependencyInjectionVersions.koin_version}" }
    val koin_core by lazy { "io.insert-koin:koin-core:${DependencyInjectionVersions.koin_version}" }
    val koin_compose by lazy { "io.insert-koin:koin-androidx-compose:${DependencyInjectionVersions.koin_version}" }
}

object DatabaseDependencies {
    val room by lazy { "androidx.room:room-runtime:${DatabaseVersions.room_version}" }
    val room_ktx by lazy { "androidx.room:room-ktx:${DatabaseVersions.room_version}" }
    val room_compiler by lazy { "androidx.room:room-compiler:${DatabaseVersions.room_version}" }
}

object AsyncDependencies {
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${AsyncVersions.coroutines_version}" }
    val coroutines_android by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${AsyncVersions.coroutines_version}" }
}

object SecurityDependencies {
    val biometric by lazy { "androidx.biometric:biometric:${SecurityVersions.biometric_version}" }
    val secure_shared_preferences by lazy { "androidx.security:security-crypto:${SecurityVersions.secure_shared_preferences_version}" }
    val datastore by lazy { "androidx.datastore:datastore-preferences:${SecurityVersions.datastore_version}" }
}

object FirebaseDependencies {
    val crashlytics by lazy { "com.google.firebase:firebase-crashlytics-ktx:${FirebaseVersions.crashlytics}" }
    val analytics by lazy { "com.google.firebase:firebase-analytics-ktx:${FirebaseVersions.analytitcs}" }
}

object TestDependencies {
    val arch by lazy { "androidx.arch.core:core-testing:${TestVersions.arch}" }
    val junit by lazy { "junit:junit:${TestVersions.junit}" }
    val junit_instrumentation by lazy { "androidx.test.ext:junit:${TestVersions.junit_instrumentation}" }
    val mockk by lazy { "io.mockk:mockk:${TestVersions.mockk}" }
    val mockito_android by lazy { "org.mockito:mockito-android:${TestVersions.mockito_android}" }
    val mockito by lazy { "org.mockito:mockito-android:${TestVersions.mockito}" }
    val truth by lazy { "com.google.truth:truth:${TestVersions.truth}" }
    val mockk_android by lazy { "io.mockk:mockk-android:${TestVersions.mockk}" }
    val coroutines_test by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestVersions.coroutines_test}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${TestVersions.espresso}" }
    val test_core by lazy { "androidx.test:core:${TestVersions.test_core}" }
    val kotlin_test by lazy { "org.jetbrains.kotlin:kotlin-test:${AndroidVersions.kotlin}" }
    val koin_test by lazy { "io.insert-koin:koin-test:${TestVersions.koin_test}" }
    val koin_test_junit by lazy { "io.insert-koin:koin-test-junit4:${TestVersions.koin_test}" }

    val jUnitCompose by lazy { "androidx.compose.ui:ui-test-junit4:${ComposeVersions.compose_version}" }
    val testManifestCompose by lazy { "androidx.compose.ui:ui-test-manifest:${ComposeVersions.compose_version}" }
    val turbine by lazy { "app.cash.turbine:turbine:${TestVersions.turbine}" }
    val nav_test by lazy { "androidx.navigation:navigation-testing:${SupportVersions.navigation_version}" }
}