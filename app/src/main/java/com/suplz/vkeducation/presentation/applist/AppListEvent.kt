package com.suplz.vkeducation.presentation.applist

sealed interface AppListEvent {
    data class AppLogoClick(
        val appName: String
    ) : AppListEvent
}