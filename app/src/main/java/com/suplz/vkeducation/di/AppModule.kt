package com.suplz.vkeducation.di

import com.suplz.vkeducation.data.appdetails.AppDetailsRepositoryImpl
import com.suplz.vkeducation.data.applist.AppListRepositoryImpl
import com.suplz.vkeducation.domain.appdetails.AppDetailsRepository
import com.suplz.vkeducation.domain.applist.AppListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}