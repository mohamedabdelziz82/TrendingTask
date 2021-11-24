package com.mohamedabdelaziz.trendingtask.data.datasource.local

import androidx.room.Database
import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse
import androidx.room.TypeConverters
import androidx.room.RoomDatabase
import com.mohamedabdelaziz.trendingtask.data.datasource.local.dao.TrendingDao
import com.mohamedabdelaziz.trendingtask.domain.entities.Converters

@Database(entities = [TrendingResponse::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TrendingDataBase : RoomDatabase() {
    abstract fun trendingDao(): TrendingDao
}