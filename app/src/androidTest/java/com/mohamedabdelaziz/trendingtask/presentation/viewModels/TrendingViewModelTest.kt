package com.mohamedabdelaziz.trendingtask.presentation.viewModels

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mohamedabdelaziz.trendingtask.data.datasource.local.TrendingDataBaseTest
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mohamedabdelaziz.trendingtask.data.datasource.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import com.mohamedabdelaziz.trendingtask.data.repository.TrendingRepositoryRemoteImp
import com.mohamedabdelaziz.trendingtask.data.repository.TrendingRepositoryLocalImp
import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4ClassRunner::class)
class TrendingViewModelTest : TrendingDataBaseTest() {
    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var viewModel: TrendingViewModel? = null

    @Mock
    var apiService: ApiService? = null
    @Before
    fun init() {
        apiService = Retrofit.Builder()
                .baseUrl("https://private-anon-ff5114eef6-githubtrendingapi.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
        viewModel = TrendingViewModel(TrendingRepositoryRemoteImp(apiService!!), TrendingRepositoryLocalImp(appDatabase?.trendingDao()!!))
    }

    @Test
    fun testTrendingViewModel() {
        viewModel?.getTrendingListRemote()
        viewModel?.trendingResponseMutableLiveData?.observeForever { trendingResponses: List<TrendingResponse> -> Assert.assertEquals(trendingResponses.size.toLong(), 4) }
    }
}