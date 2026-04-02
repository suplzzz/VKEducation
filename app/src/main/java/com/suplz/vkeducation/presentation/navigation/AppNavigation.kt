package com.suplz.vkeducation.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.suplz.vkeducation.presentation.appdetails.AppDetailsScreen
import com.suplz.vkeducation.presentation.applist.AppListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AppList.route) {

        composable(Screen.AppList.route) {
            AppListScreen(
                onClick = { appId ->
                    navController.navigate(Screen.AppDetails.createRoute(appId))
                }
            )
        }

        composable(
            route = Screen.AppDetails.route,
            arguments = listOf(navArgument("appId") { type = NavType.StringType })
        ) {
            AppDetailsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
sealed class Screen(val route: String) {
    data object AppList : Screen("appList")
    data object AppDetails : Screen("appDetails/{appId}") {
        fun createRoute(appId: String) = "appDetails/$appId"
    }
}