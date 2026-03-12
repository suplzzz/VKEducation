package com.suplz.vkeducation.mapper

import com.suplz.vkeducation.model.Category

fun Category.toUiString(): String {
    return when (this) {
        Category.APP -> "Приложение"
        Category.GAME -> "Игра"
    }
}