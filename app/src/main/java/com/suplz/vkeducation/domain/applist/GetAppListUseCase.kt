package com.suplz.vkeducation.domain.applist

import javax.inject.Inject

class GetAppListUseCase @Inject constructor(
    private val repository: AppListRepository
) {
    suspend operator fun invoke() : List<AppSummary> {
        val apps: List<AppSummary> = repository.get()

        return apps
    }
}