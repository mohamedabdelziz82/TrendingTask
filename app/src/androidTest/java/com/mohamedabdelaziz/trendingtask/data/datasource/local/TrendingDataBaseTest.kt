package com.mohamedabdelaziz.trendingtask.data.datasource.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class TrendingDataBaseTest {
    lateinit var appDatabase: TrendingDataBase
    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                TrendingDataBase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        appDatabase!!.close()
    }
}