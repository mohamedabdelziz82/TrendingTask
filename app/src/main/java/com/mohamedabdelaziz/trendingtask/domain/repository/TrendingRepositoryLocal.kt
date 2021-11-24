package com.mohamedabdelaziz.trendingtask.domain.repository

import androidx.lifecycle.LiveData
import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse

interface TrendingRepositoryLocal {
    fun insertTrendingList(trendingList: List<TrendingResponse>)
    fun deleteAllTrendingList()
    fun getTrendingList(): LiveData<List<TrendingResponse>>
}