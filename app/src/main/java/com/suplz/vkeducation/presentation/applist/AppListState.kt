package com.suplz.vkeducation.presentation.applist

import androidx.compose.runtime.Immutable
import com.suplz.vkeducation.domain.applist.AppSummary

@Immutable
sealed interface AppListState {
    data object Error : AppListState

    data object Loading : AppListState

    data class Content(
        val appList: List<AppSummary>,
    ) : AppListState
}