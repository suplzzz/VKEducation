package com.suplz.vkeducation.domain.applist

interface AppListRepository {
    suspend fun get() : List<AppSummary>
}