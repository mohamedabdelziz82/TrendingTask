package com.mohamedabdelaziz.trendingtask.domain.repository

import com.mohamedabdelaziz.trendingtask.domain.entities.TrendingResponse
import io.reactivex.rxjava3.core.Single

interface TrendingRepositoryRemote {
    fun getTrendingList(): Single<List<TrendingResponse>>
}