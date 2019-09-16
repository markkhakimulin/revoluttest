package com.markkhakimulin.revolut.presentator.di.modules

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.markkhakimulin.revolut.domain.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Mark Khakimulin on 04.09.2019.
 * Email : mark.khakimulin@gmail.com
 */

@Module(includes = [ViewModelsModule::class])
object  ApiServiceModule {

    private const val BASE_URL = "https://revolut.duckdns.org/"

    @JvmStatic
    @Provides
    @Singleton
    fun getServiceUtil(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java)

    @JvmStatic
    @Singleton
    @Provides
    fun getGson() = GsonBuilder().create()!!

    @JvmStatic
    @Singleton
    @Provides
    fun getGsonFactory() = GsonConverterFactory.create()!!

    @JvmStatic
    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient, gsonf: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(gsonf)
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }
    @JvmStatic
    @Provides
    @Singleton
    fun getInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor {
            Timber.i(it)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @JvmStatic
    @Provides
    @Singleton
    fun getOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .cache(null)
            .addInterceptor(interceptor)
            .build()
    }
}