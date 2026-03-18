package com.suplz.vkeducation.presentation.applist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.suplz.vkeducation.presentation.applist.components.AppListContent
import com.suplz.vkeducation.presentation.applist.components.AppListTopBar
import com.suplz.vkeducation.presentation.components.ErrorScreen
import com.suplz.vkeducation.presentation.components.LoadingScreen
import com.suplz.vkeducation.presentation.ui.theme.RuStoreBlue
import com.suplz.vkeducation.presentation.ui.theme.VKEducationTheme
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
                    contentPadding = innerPadding,
                    onClick = onClick,
                    onLogoClick = { appName ->
                        viewModel.onLogoClicked(appName)
                    }
                )
            }

            AppListState.Error -> {
                ErrorScreen(
                    onRefreshClick = { viewModel.getApps() },
                    modifier = Modifier
                        .padding(top = innerPadding.calculateTopPadding())
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(MaterialTheme.colorScheme.background),
                )
            }

            AppListState.Loading -> {
                LoadingScreen(
                    modifier = Modifier
                        .padding(top = innerPadding.calculateTopPadding())
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(MaterialTheme.colorScheme.background),
                )
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


