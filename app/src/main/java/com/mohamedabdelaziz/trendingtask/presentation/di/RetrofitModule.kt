package com.mohamedabdelaziz.trendingtask.presentation.di

import dagger.hilt.InstallIn
import javax.inject.Singleton
import com.mohamedabdelaziz.trendingtask.data.datasource.remote.ApiService
import com.mohamedabdelaziz.trendingtask.domain.repository.TrendingRepositoryRemote
import com.mohamedabdelaziz.trendingtask.data.repository.TrendingRepositoryRemoteImp
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Singleton
    @Provides
    fun provideTrendingRepositoryRemote(apiService: ApiService): TrendingRepositoryRemote = TrendingRepositoryRemoteImp(apiService)


    @Provides
    @Singleton
    fun providesOkHttpClientBuilder(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build()


    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl("https://private-anon-ff5114eef6-githubtrendingapi.apiary-mock.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()


    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}