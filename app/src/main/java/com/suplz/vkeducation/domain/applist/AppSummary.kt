package com.suplz.vkeducation.domain.applist

import com.suplz.vkeducation.domain.Category

data class AppSummary(
    val name: String,
    val category: Category,
    val iconUrl: String,
    val description: String,
)