package com.mohamedabdelaziz.trendingtask.domain.usecase


import javax.inject.Inject
import com.mohamedabdelaziz.trendingtask.domain.repository.TrendingRepositoryLocal


class TrendingLocalUseCase @Inject constructor(var repository: TrendingRepositoryLocal)
