package br.com.jwk.thefixt.module

import br.com.jwk.thefixt.BuildConfig
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.applicationContext
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal const val OMDB_URL = "OMDB_URL"
private const val READ_TIME_OUT = "READ_TIME_OUT"
private const val CONNECT_TIME_OUT = "CONNECT_TIME_OUT"
private const val HEADER_INTERCEPTOR = "HEADER_INTERCEPTOR"
private const val LOGGER_INTERCEPTOR = "LOGGER_INTERCEPTOR"

val retrofitClientModule = applicationContext {

    bean(OMDB_URL) { "http://www.omdbapi.com" }
    bean(READ_TIME_OUT) { 60 }
    bean(CONNECT_TIME_OUT) { 60 }

    factory {
        Retrofit.Builder()
                .baseUrl(get<String>(OMDB_URL))
                .client(/* OkHttpClient */ get())
                .addConverterFactory(/*Converter.Factory*/ get())
                .build()
    }

    bean {
        OkHttpClient.Builder()
                .connectTimeout(get(CONNECT_TIME_OUT), TimeUnit.SECONDS)
                .readTimeout(get(READ_TIME_OUT), TimeUnit.SECONDS)
                .addInterceptor(get(HEADER_INTERCEPTOR))
                .addInterceptor(get(LOGGER_INTERCEPTOR))
                .build() as OkHttpClient
    }

    bean(HEADER_INTERCEPTOR) {
        Interceptor { chain ->
            chain.proceed(chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .build())
        }
    }

    bean(LOGGER_INTERCEPTOR) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    bean { GsonConverterFactory.create(/* Gson */ get()) as Converter.Factory }

    bean {
        Gson()
    }
}