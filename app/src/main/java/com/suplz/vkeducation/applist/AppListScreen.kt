package com.suplz.vkeducation.applist

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.suplz.vkeducation.ui.theme.RuStoreBlue
import com.suplz.vkeducation.ui.theme.VKEducationTheme
import kotlinx.coroutines.flow.Flow

@Composable
fun AppListScreen(
    onClick: () -> Unit,
) {

    val viewModel = viewModel<AppListViewModel>()
    val state by viewModel.state.collectAsState()
    val events = viewModel.events

    val snackbarHostState = remember { SnackbarHostState() }

    ObserveEvents(
        events = events,
        snackbarHostState = snackbarHostState,
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        containerColor = RuStoreBlue,
        topBar = { AppListTopBar() }

    ) { innerPadding ->
        when (val currentState = state) {
            is AppListState.Content -> {
                AppListContent(
                    content = currentState,
                    innerPadding = innerPadding,
                    onClick = onClick,
                    onLogoClick = {appName ->
                        viewModel.onLogoClicked(appName)
                    }
                )
            }

            AppListState.Error -> {
                AppListError(
                    onRefreshClick = { viewModel.getApps() },
                    innerPadding = innerPadding
                )
            }

            AppListState.Loading -> {
                AppListLoading(innerPadding = innerPadding)
            }
        }
    }
}


@Composable
private fun ObserveEvents(
    events: Flow<AppListEvent>,
    snackbarHostState: SnackbarHostState,
) {
    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is AppListEvent.AppLogoClick -> {
                    snackbarHostState.showSnackbar(event.appName)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppListScreenPreview() {
    VKEducationTheme {
        AppListScreen(
            onClick = {}
        )
    }
}


