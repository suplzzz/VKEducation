package com.suplz.vkeducation.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suplz.vkeducation.data.applist.AppListRepositoryImpl
import com.suplz.vkeducation.domain.applist.GetAppListUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel : ViewModel() {
    private val getAppListUseCase = GetAppListUseCase(
        repository = AppListRepositoryImpl()
    )

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
                _state.value = AppListState.Error
            }
        }
    }
}