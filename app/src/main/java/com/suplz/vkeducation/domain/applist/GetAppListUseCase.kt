package com.suplz.vkeducation.domain.applist

class GetAppListUseCase(
    private val repository: AppListRepository
) {
    suspend operator fun invoke() : List<AppSummary> {
        val apps: List<AppSummary> = repository.get()

        return apps
    }
}