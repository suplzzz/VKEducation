package com.suplz.vkeducation.presentation.appdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suplz.vkeducation.domain.appdetails.GetAppDetailsUseCase
import com.suplz.vkeducation.domain.appdetails.ObserveAppDetailsUseCase
import com.suplz.vkeducation.domain.appdetails.ToggleWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    private val observeAppDetailsUseCase: ObserveAppDetailsUseCase,
    private val toggleWishlistUseCase: ToggleWishlistUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val appId: String = checkNotNull(savedStateHandle["appId"])

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        observeAppDetails()
        loadInitialData()
    }

    private fun observeAppDetails() {
        viewModelScope.launch {
            observeAppDetailsUseCase(appId)
                .catch {
                    _state.value = AppDetailsState.Error
                }
                .collect { appDetails ->
                    _state.value = AppDetailsState.Content(
                        appDetails = appDetails,
                        descriptionCollapsed = false,
                        isInWishlist = appDetails.isInWishlist
                    )
                }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            runCatching {
                getAppDetailsUseCase(appId)
            }.onFailure {
                _state.value = AppDetailsState.Error
            }
        }
    }

    fun retryGetAppDetails() {
        _state.value = AppDetailsState.Loading
        loadInitialData()
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            runCatching {
                toggleWishlistUseCase(appId)
            }
        }
    }

    fun showUnderDevelopmentMessage() {
        viewModelScope.launch {
            _events.send(AppDetailsEvent.UnderDevelopment)
        }
    }

    fun collapseDescription() {
        _state.update { currentState ->
            if (currentState is AppDetailsState.Content) {
                currentState.copy(descriptionCollapsed = true)
            } else {
                currentState
            }
        }
    }
}