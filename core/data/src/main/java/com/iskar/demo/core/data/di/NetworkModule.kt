package com.iskar.demo.core.data.di

import com.google.gson.Gson
import com.iskar.demo.core.data.BuildConfig
import com.iskar.demo.core.data.utils.trustAllCertificates
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    factory { GsonConverterFactory.create(get()) }

    single {
        OkHttpClient.Builder()
            .apply {
                if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M) {
                    trustAllCertificates()
                }
            }
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get())
            .build()
    }

    single { Gson() }

}
