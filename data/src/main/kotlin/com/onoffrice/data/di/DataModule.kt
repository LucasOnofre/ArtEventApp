package com.onoffrice.data.di

import androidx.room.Room
import com.onoffrice.data.BuildConfig
import com.onoffrice.data.api.BaseUrl
import com.onoffrice.data.api.ArtEventApi
import com.onoffrice.data.database.ArtEventDatabase
import okhttp3.Cache
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Invocation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.Collections
import java.util.concurrent.TimeUnit

const val CONN_TIMEOUT_SEC = 60L

val dataModule = module {
    single {
        HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BASIC }
    }

    single {
        Interceptor { chain ->
            val original = chain.request()

            val baseUrl = original
                .tag(Invocation::class.java)
                ?.method()
                ?.getAnnotation(BaseUrl::class.java)?.value
                ?: throw IOException("You must add ApiType to your request method in interface.")

            val endpoint = original.url.toUrl().file.substringAfter("v1/")

            val request = original.newBuilder()
                .header("Accept", "application/json; charset=utf-8")
                .url(baseUrl.plus(endpoint))
                .build()
            chain.proceed(request)
        }
    }

    single {
        val spec = ConnectionSpec
            .Builder(ConnectionSpec.COMPATIBLE_TLS)
            .allEnabledCipherSuites()
            .build()

        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(androidContext().cacheDir, cacheSize)

        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(get<Interceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectionSpecs(Collections.singletonList(spec))
            .connectTimeout(CONN_TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(CONN_TIMEOUT_SEC, TimeUnit.SECONDS)
            .writeTimeout(CONN_TIMEOUT_SEC, TimeUnit.SECONDS)
            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single { get<Retrofit>().create(ArtEventApi::class.java) }

    single {
        Room
            .databaseBuilder(androidContext(), ArtEventDatabase::class.java, "db")
            .build()
    }

}
