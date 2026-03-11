package com.suplz.vkeducation.applist

import com.suplz.vkeducation.appdetails.Category

fun Category.toUiString(): String {
    return when (this) {
        Category.APP -> "Приложение"
        Category.GAME -> "Игра"
    }
}