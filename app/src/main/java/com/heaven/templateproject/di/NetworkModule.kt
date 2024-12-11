package com.heaven.templateproject.di

import com.google.gson.GsonBuilder
import com.heaven.templateproject.network.api.TestApi
import com.heaven.templateproject.network.responseHandling.adapter.RestApiResponseAdapterFactory
import com.heaven.templateproject.network.urlprovider.UrlProvider
import com.heaven.templateproject.network.urlprovider.UrlProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val BASE_URL = "https://raw.githubusercontent.com/Nevaehy/"

    @Provides
    fun provideRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RestApiResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideApiClient(retrofit: Retrofit): TestApi {
        return retrofit.create(TestApi::class.java)
    }

    @Provides
    fun provideTestUrlProvider(impl: UrlProviderImpl): UrlProvider {
        return impl
    }
}