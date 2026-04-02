package com.suplz.vkeducation.data.applist

import com.suplz.vkeducation.data.AppApi
import com.suplz.vkeducation.domain.applist.AppListRepository
import com.suplz.vkeducation.domain.applist.AppSummary
import javax.inject.Inject

class AppListRepositoryImpl @Inject constructor(
    private val appApi: AppApi,
    private val mapper: AppSummaryMapper
) : AppListRepository {

    override suspend fun get(): List<AppSummary> {
        val dto = appApi.getAppList()
        val domain = mapper.toDomainList(dto)
        return domain
    }
}