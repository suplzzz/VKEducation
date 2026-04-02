package com.suplz.vkeducation.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.suplz.vkeducation.R
import com.suplz.vkeducation.domain.Category

@Composable
fun Category.toUiString() = when (this) {
    Category.APP -> stringResource(R.string.category_app)
    Category.GAME -> stringResource(R.string.category_game)
    Category.PRODUCTIVITY -> stringResource(R.string.productivity)
    Category.SOCIAL -> stringResource(R.string.social)
    Category.EDUCATION -> stringResource(R.string.education)
    Category.ENTERTAINMENT -> stringResource(R.string.entertaiment)
    Category.MUSIC -> stringResource(R.string.music)
    Category.VIDEO -> stringResource(R.string.video)
    Category.PHOTOGRAPHY -> stringResource(R.string.photography)
    Category.HEALTH -> stringResource(R.string.health)
    Category.SPORTS -> stringResource(R.string.sports)
    Category.NEWS -> stringResource(R.string.news)
    Category.BOOKS -> stringResource(R.string.books)
    Category.BUSINESS -> stringResource(R.string.business)
    Category.FINANCE -> stringResource(R.string.finance)
    Category.TRAVEL -> stringResource(R.string.travel)
    Category.MAPS -> stringResource(R.string.maps)
    Category.FOOD -> stringResource(R.string.food)
    Category.SHOPPING -> stringResource(R.string.shopping)
    Category.UTILITIES -> stringResource(R.string.utilites)
    Category.COMMUNICATION -> stringResource(R.string.communication)
    Category.NAVIGATION -> stringResource(R.string.navigation)
    Category.WEATHER -> stringResource(R.string.weather)
}