package com.zkylab.foodappcompose.feature.di

import android.app.Application
import androidx.room.Room
import com.zkylab.foodappcompose.feature.data.remote.FoodApi
import com.zkylab.foodappcompose.feature.data.repository.FoodRepositoryImpl
import com.zkylab.foodappcompose.feature.domain.repository.FoodRepository
import com.zkylab.foodappcompose.feature.domain.usecase.GetFoodUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoodModule {

    @Provides
    @Singleton
    fun provideGetFoodUseCase(repository: FoodRepository): GetFoodUseCase {
        return GetFoodUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFoodRepository(
        api: FoodApi,
    ): FoodRepository {
        return FoodRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideFoodApi(): FoodApi {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
//                    .header("X-Api-Key", "Sdrhz4DB2sdUOk+hA2g1Jg==4ik8r56usWmV4bXA")
                    .build()
                chain.proceed(request)
            }
//            .connectTimeout(30, TimeUnit.SECONDS) // Set your preferred timeout duration
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://192.168.43.112:80/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(FoodApi::class.java)

    }

}