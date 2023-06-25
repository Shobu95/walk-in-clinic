package com.shobu.walk_in_clinic.ui.navigation.home_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shobu.walk_in_clinic.ui.main.home.HomeScreen
import com.shobu.walk_in_clinic.ui.main.my_appointment.MyAppointmentsScreen
import com.shobu.walk_in_clinic.ui.main.profile.ProfileScreen

@Composable
fun MainNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = MainNavRoutes.Home.route
    ) {

        composable(MainNavRoutes.Home.route) {
            HomeScreen(
                title = MainNavRoutes.Home.title,
                navController = navController
            )
        }

        composable(MainNavRoutes.MyAppointments.route) {
            MyAppointmentsScreen(
                title = MainNavRoutes.MyAppointments.title,
            )
        }

        composable(MainNavRoutes.Profile.route) {
            ProfileScreen(
                MainNavRoutes.Profile.title,
            )
        }

    }
}