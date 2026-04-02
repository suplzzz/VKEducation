package com.suplz.vkeducation.data.applist

import com.suplz.vkeducation.domain.Category
import kotlinx.serialization.Serializable

@Serializable
data class AppSummaryDto(
    val id: String,
    val name: String,
    val category: Category,
    val iconUrl: String,
    val description: String,
)
