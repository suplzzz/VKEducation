package com.suplz.vkeducation.data

import androidx.room.TypeConverter
import com.suplz.vkeducation.domain.Category

class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: Category): String = category.name

    @TypeConverter
    fun toCategory(categoryName: String): Category = Category.valueOf(categoryName)
}