package com.fakhry.katakerjaapps.core.di

import com.fakhry.katakerjaapps.core.BuildConfig
import com.fakhry.katakerjaapps.core.const.API
import com.fakhry.katakerjaapps.core.data.source.remote.network.BookApiService
import com.fakhry.katakerjaapps.core.data.source.remote.network.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG)
                this.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideUserApiService(client: OkHttpClient): UserApiService =
        retrofitBuilder(client).create(UserApiService::class.java)


    @Provides
    fun provideBookApiService(client: OkHttpClient): BookApiService =
        retrofitBuilder(client).create(BookApiService::class.java)

    private fun retrofitBuilder(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(API.BASE_API)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}