package com.suplz.vkeducation.data.applist

import com.suplz.vkeducation.domain.applist.AppSummary
import javax.inject.Inject

class AppSummaryMapper @Inject constructor() {
    fun toDomain(dto: AppSummaryDto) : AppSummary = AppSummary(
        id = dto.id,
        name = dto.name,
        category = dto.category,
        iconUrl = dto.iconUrl,
        description = dto.description
    )

    fun toDomainList(dto: List<AppSummaryDto>): List<AppSummary> =
        dto.map { toDomain(it) }
}