package com.suplz.vkeducation.data.applist

import com.suplz.vkeducation.data.AppApi
import com.suplz.vkeducation.data.CategoryMapper
import com.suplz.vkeducation.domain.applist.AppListRepository
import com.suplz.vkeducation.domain.applist.AppSummary

class AppListRepositoryImpl : AppListRepository {
    private val appApi = AppApi()
    private val categoryMapper = CategoryMapper()
    private val mapper = AppSummaryMapper(categoryMapper)
    override suspend fun get(): List<AppSummary> {
        val dto = appApi.getAppList()
        val domain = mapper.toDomainList(dto)
        return domain
    }
}