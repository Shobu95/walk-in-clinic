package com.shobu.walk_in_appointment.ui.navigation.home_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shobu.walk_in_appointment.ui.auth.launch.LaunchScreen
import com.shobu.walk_in_appointment.ui.auth.login.LoginScreen
import com.shobu.walk_in_appointment.ui.auth.signup.SignupScreen
import com.shobu.walk_in_appointment.ui.home.my_appointment.MyAppointmentsScreen
import com.shobu.walk_in_appointment.ui.home.profile.ProfileScreen
import com.shobu.walk_in_appointment.ui.home.upcoming_appointment.UpcomingAppointmentScreen
import com.shobu.walk_in_appointment.ui.navigation.auth_nav.AuthNavRoutes

@Composable
fun HomeNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = HomeNavRoutes.Home.route) {

        composable(HomeNavRoutes.Home.route) {
            UpcomingAppointmentScreen(
                title = HomeNavRoutes.Home.title,
                navController = navController
            )
        }

        composable(HomeNavRoutes.MyAppointments.route) {
            MyAppointmentsScreen(
                title = HomeNavRoutes.MyAppointments.title,
            )
        }

        composable(HomeNavRoutes.Profile.route) {
            ProfileScreen(
                HomeNavRoutes.Profile.title,
            )
        }

    }
}