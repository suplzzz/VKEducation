package com.suplz.vkeducation.mapper

import androidx.annotation.StringRes
import com.suplz.vkeducation.R
import com.suplz.vkeducation.model.Category

val Category.toUiString: Int
    @StringRes get() = when (this) {
        Category.APP -> R.string.category_app
        Category.GAME -> R.string.category_game
    }