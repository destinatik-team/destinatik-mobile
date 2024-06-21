package com.dicoding.destinatik.core.module

import android.util.Log
import com.dicoding.destinatik.BuildConfig
import com.dicoding.destinatik.core.data.local.preferences.auth.AuthPreferences
import com.dicoding.destinatik.core.data.remote.client.ApiServices
import com.dicoding.destinatik.core.data.remote.client.MainApiServices
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { AuthPreferences(get()) }

    single {
        val authPreferences: AuthPreferences = get()
        Interceptor { chain ->
            val token = authPreferences.getToken()
            val request = if (!token.isNullOrEmpty()) {
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                // Token null atau kosong, lakukan penanganan di sini
                println("Error: Token is null or empty")
                Log.e("AuthInterceptor", "Error: Token is null or empty $token")
                chain.request() // Lanjutkan permintaan tanpa token
            }
            chain.proceed(request)
        }
    }

    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor(loggingInterceptor)
            .build()
    }
}

val retrofitModule = module {
    single(named("default")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    single(named("main")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_MAIN)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }


    single {
        get<Retrofit>(named("default")).create(ApiServices::class.java)
    }

    single {
        get<Retrofit>(named("main")).create(MainApiServices::class.java)
    }
}