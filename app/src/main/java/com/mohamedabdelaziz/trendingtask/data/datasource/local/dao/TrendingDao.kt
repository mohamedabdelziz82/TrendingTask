package com.mohamedabdelaziz.trendingtask.data.datasource.local.dao

import androidx.room.Dao
import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TrendingDao {
    @Insert
    fun insertTrendingList(trendingList: List<TrendingResponse?>?)

    @Query("delete from trend_table")
    fun deleteAllTrendingList()

    @Query("select * from trend_table")
    fun getAllTrendingFromDB(): LiveData<List<TrendingResponse>>
}