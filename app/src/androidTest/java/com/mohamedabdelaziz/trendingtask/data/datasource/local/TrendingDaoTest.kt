package com.mohamedabdelaziz.trendingtask.data.datasource.local

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.ArrayList

@RunWith(AndroidJUnit4ClassRunner::class)
class TrendingDaoTest : TrendingDataBaseTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertTrendingTest() {
        val trendingResponse1 = TrendingResponse()
        val trendingResponse2 = TrendingResponse()
        val trendingResponse3 = TrendingResponse()
        val trendingResponse4 = TrendingResponse()
        val trendingResponseList: MutableList<TrendingResponse?> = ArrayList()
        trendingResponseList.add(trendingResponse1)
        trendingResponseList.add(trendingResponse2)
        trendingResponseList.add(trendingResponse3)
        trendingResponseList.add(trendingResponse4)
        appDatabase?.trendingDao()?.insertTrendingList(trendingResponseList)
        appDatabase?.trendingDao()?.getAllTrendingFromDB()?.observeForever { trendingList ->
            Assert.assertEquals(trendingList?.size!!, 4)
        }

    }

    @Test
    fun deleteTrendingTest() {
        appDatabase?.trendingDao()?.deleteAllTrendingList()
        appDatabase?.trendingDao()?.getAllTrendingFromDB()
            ?.observeForever { trendingList -> Assert.assertEquals(trendingList?.size!!, 0) }
    }
}