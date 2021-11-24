package com.mohamedabdelaziz.trendingtask.data.repository

import javax.inject.Inject
import com.mohamedabdelaziz.trendingtask.data.datasource.remote.ApiService
import com.mohamedabdelaziz.trendingtask.domain.repository.TrendingRepositoryRemote
import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse
import io.reactivex.rxjava3.core.Single

/**
 * This repository is responsible for
 * fetching data from server or db
 */
class TrendingRepositoryRemoteImp @Inject constructor(private val apiService: ApiService) : TrendingRepositoryRemote {
       override fun getTrendingList(): Single<List<TrendingResponse>> = apiService.getApiTrendingRepositoriesList()

}