package com.mohamedabdelaziz.trendingtask.presentation.di

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.mohamedabdelaziz.trendingtask.data.datasource.local.dao.TrendingDao
import com.mohamedabdelaziz.trendingtask.domain.repository.TrendingRepositoryLocal
import com.mohamedabdelaziz.trendingtask.data.repository.TrendingRepositoryLocalImp
import android.app.Application
import com.mohamedabdelaziz.trendingtask.data.datasource.local.TrendingDataBase
import androidx.room.Room
import dagger.Module
import dagger.Provides

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideTrendingRepositoryLocal(trendingDao: TrendingDao): TrendingRepositoryLocal= TrendingRepositoryLocalImp(trendingDao)



        @Provides
        @Singleton
        fun provideDB(application: Application): TrendingDataBase = Room.databaseBuilder(application, TrendingDataBase::class.java, "trending_DB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()


        @Provides
        @Singleton
        fun provideDao(trendingDataBase: TrendingDataBase): TrendingDao=  trendingDataBase.trendingDao()


}