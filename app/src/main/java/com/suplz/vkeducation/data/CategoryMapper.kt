package com.suplz.vkeducation.data

import com.suplz.vkeducation.domain.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor(){
    fun toDomain(category: String) : Category {
        return when (category.lowercase()) {
            "game" -> Category.GAME
            else -> Category.APP
        }
    }
}