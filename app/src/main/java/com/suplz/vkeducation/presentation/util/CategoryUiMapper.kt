package com.suplz.vkeducation.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.suplz.vkeducation.R
import com.suplz.vkeducation.domain.Category

@Composable
fun Category.toUiString() = when (this) {
    Category.APP -> stringResource(R.string.category_app)
    Category.GAME -> stringResource(R.string.category_game)
}