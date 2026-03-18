package com.suplz.vkeducation.data.appdetails

import com.suplz.vkeducation.domain.Category

data class AppDetailsDto(
    val name: String,
    val developer: String,
    val category: Category,
    val ageRating: Int,
    val size: Double,
    val icon: String,
    val screenshots: List<String>,
    val description: String,
)