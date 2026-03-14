package com.suplz.vkeducation.data.applist

import com.suplz.vkeducation.data.CategoryMapper
import com.suplz.vkeducation.domain.applist.AppSummary

class AppSummaryMapper(
    private val categoryMapper: CategoryMapper
) {
    fun toDomain(dto: AppSummaryDto) : AppSummary = AppSummary(
        name = dto.name,
        category = categoryMapper.toDomain(dto.category),
        iconUrl = dto.iconUrl,
        description = dto.description
    )

    fun toDomainList(dto: List<AppSummaryDto>): List<AppSummary> =
        dto.map { toDomain(it) }
}