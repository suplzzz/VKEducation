package com.suplz.vkeducation

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suplz.vkeducation.appdetails.AppDetailsScreen
import com.suplz.vkeducation.applist.AppListScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AppList.route) {

        composable(Screen.AppList.route) {
            AppListScreen(
                onClick = {
                    navController.navigate(Screen.AppDetails.route)
                }
            )
        }

        composable(Screen.AppDetails.route) {
            AppDetailsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object AppList: Screen("appList")
    data object AppDetails: Screen("appDetails")
}