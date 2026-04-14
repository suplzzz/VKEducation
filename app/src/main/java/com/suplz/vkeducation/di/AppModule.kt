package com.suplz.vkeducation.di

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.suplz.vkeducation.data.AppApi
import com.suplz.vkeducation.data.AppDatabase
import com.suplz.vkeducation.data.appdetails.AppDetailsRepositoryImpl
import com.suplz.vkeducation.data.appdetails.local.AppDetailsDao
import com.suplz.vkeducation.data.applist.AppListRepositoryImpl
import com.suplz.vkeducation.domain.appdetails.AppDetailsRepository
import com.suplz.vkeducation.domain.applist.AppListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun bindAppDetailsRepository(impl: AppDetailsRepositoryImpl) : AppDetailsRepository

    @Binds
    @Singleton
    fun bindAppListRepository(impl: AppListRepositoryImpl) : AppListRepository

    companion object {

        @Provides
        @Singleton
        fun provideJson(): Json {
            return Json {
                ignoreUnknownKeys = true
            }
        }

        @Provides
        @Singleton
        fun provideConverterFactory(
            json: Json
        ): Converter.Factory {
            return json.asConverterFactory(
                "application/json".toMediaType()
            )
        }

        @Provides
        @Singleton
        fun provideRetrofit(
            converterFactory: Converter.Factory
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://185.103.109.134")
                .addConverterFactory(converterFactory)
                .build()
        }

        @Provides
        @Singleton
        fun provideApiService(
            retrofit: Retrofit
        ): AppApi {
            return retrofit.create()
        }

        @Provides
        @Singleton
        fun provideDatabase(app: Application): AppDatabase {
            return Room.databaseBuilder(
                app,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            ).build()
        }

        @Provides
        @Singleton
        fun provideAppDetailsDao(database: AppDatabase): AppDetailsDao {
            return database.appDetailsDao()
        }
    }
}