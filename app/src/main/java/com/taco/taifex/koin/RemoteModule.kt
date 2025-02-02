package com.taco.taifex.koin

import com.taco.taifex.domain.Config
import com.taco.taifex.domain.server.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy

val remoteModule = module {
    single { provideCookieManager() }
    single { provideRetrofit() }

    // Api
    factory { provideBotAPI(get()) }
}

fun provideBotAPI(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

fun provideCookieManager(): CookieManager {
    val cookieManager = CookieManager()
    cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
    return cookieManager
}

fun provideRetrofit(): Retrofit {
    val httpClient = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    httpClient.addInterceptor(logging)

    return Retrofit.Builder()
        .baseUrl(Config.BASE_URL) //Base URL required
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
}