package com.mohamedabdelaziz.trendingtask.domain.usecase

import javax.inject.Inject
import com.mohamedabdelaziz.trendingtask.domain.repository.TrendingRepositoryRemote


class TrendingRemoteUseCase @Inject constructor(var repository: TrendingRepositoryRemote)