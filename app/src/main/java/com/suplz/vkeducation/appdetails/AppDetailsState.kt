package com.suplz.vkeducation.appdetails

import androidx.compose.runtime.Immutable
import com.suplz.vkeducation.model.AppDetails

@Immutable
sealed interface AppDetailsState {
    data object Error : AppDetailsState
    data object Loading : AppDetailsState
    data class Content(
        val appDetails: AppDetails,
        val descriptionCollapsed: Boolean,
    ) : AppDetailsState
}