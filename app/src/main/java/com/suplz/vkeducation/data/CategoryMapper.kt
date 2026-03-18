package com.suplz.vkeducation.data

import com.suplz.vkeducation.domain.Category

class CategoryMapper {
    fun toDomain(category: String) : Category {
        return when (category.lowercase()) {
            "game" -> Category.GAME
            else -> Category.APP
        }
    }
}