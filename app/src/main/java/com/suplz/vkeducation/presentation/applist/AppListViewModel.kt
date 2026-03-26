package com.suplz.vkeducation.presentation.applist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suplz.vkeducation.domain.applist.GetAppListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val getAppListUseCase: GetAppListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppListEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        getApps()
    }

    fun onLogoClicked(appName: String) {
        viewModelScope.launch {
            _events.send(AppListEvent.AppLogoClick(appName))
        }
    }

    fun getApps() {
        viewModelScope.launch {
            _state.value = AppListState.Loading

            runCatching {
                val apps = getAppListUseCase()

                _state.value = AppListState.Content(apps)

            }.onFailure {
                Log.d("HOHOHO", "ERROR : $it")
                _state.value = AppListState.Error
            }
        }
    }
}