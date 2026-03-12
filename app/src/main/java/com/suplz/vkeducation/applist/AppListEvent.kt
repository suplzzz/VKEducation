package com.suplz.vkeducation.applist

sealed interface AppListEvent {
    data class AppLogoClick(
        val appName: String
    ) : AppListEvent
}