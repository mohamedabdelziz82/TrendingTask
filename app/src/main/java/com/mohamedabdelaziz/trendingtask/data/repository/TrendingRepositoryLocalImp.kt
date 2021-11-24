package com.mohamedabdelaziz.trendingtask.data.repository


import javax.inject.Inject
import com.mohamedabdelaziz.trendingtask.data.datasource.local.dao.TrendingDao
import com.mohamedabdelaziz.trendingtask.domain.repository.TrendingRepositoryLocal
import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse
import androidx.lifecycle.LiveData

/**
 * This repository is responsible for
 * fetching data from db
 */
class TrendingRepositoryLocalImp @Inject constructor(private val trendingDao: TrendingDao) :
    TrendingRepositoryLocal {
    override fun insertTrendingList(trendingList: List<TrendingResponse>) {
        trendingDao.insertTrendingList(trendingList)
    }

    override fun deleteAllTrendingList() {
        trendingDao.deleteAllTrendingList()
    }

    override fun getTrendingList(): LiveData<List<TrendingResponse>> {
        return trendingDao.getAllTrendingFromDB()
    }
}