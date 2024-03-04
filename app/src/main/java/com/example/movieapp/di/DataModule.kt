package com.example.movieapp.di

import com.example.movieapp.data.cache.MovieCacheDataSource
import com.example.movieapp.data.cache.MovieCacheDataSourceImpl
import com.example.movieapp.data.cache.dao.MovieDao
import com.example.movieapp.data.cloud.remote.MovieService
import com.example.movieapp.data.cloud.utils.Constants.API_KEY
import com.example.movieapp.data.cloud.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideMovieCacheDataSource(
        movieDao: MovieDao
    ): MovieCacheDataSource = MovieCacheDataSourceImpl(movieDao)

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(
                        Interceptor { chain ->
                            val request = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer $API_KEY").build()
                            return@Interceptor chain.proceed(request = request)
                        },
                    ).build()
            ).build()
    }

    @Provides
    fun provideWeatherService(
        retrofit: Retrofit
    ): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}
