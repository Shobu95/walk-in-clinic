package com.app.walk_in_clinic.ui.navigation.home_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.walk_in_clinic.ui.main.home.HomeScreen

@Composable
fun MainNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = MainNavRoutes.Home.route
    ) {

        composable(MainNavRoutes.Home.route) {
            HomeScreen(
                title = MainNavRoutes.Home.title,
            )
        }

    }
}