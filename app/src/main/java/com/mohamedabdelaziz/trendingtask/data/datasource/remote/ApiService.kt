package com.mohamedabdelaziz.trendingtask.data.datasource.remote

import retrofit2.http.GET
import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse
import io.reactivex.rxjava3.core.Single

interface ApiService {
    @GET("repositories")
    fun getApiTrendingRepositoriesList(): Single<List<TrendingResponse>>
}