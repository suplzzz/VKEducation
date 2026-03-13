package com.suplz.vkeducation.applist

import androidx.compose.runtime.Immutable
import com.suplz.vkeducation.model.AppSummary

@Immutable
sealed interface AppListState {
    data object Error : AppListState

    data object Loading : AppListState

    data class Content(
        val appList: List<AppSummary>,
    ) : AppListState
}