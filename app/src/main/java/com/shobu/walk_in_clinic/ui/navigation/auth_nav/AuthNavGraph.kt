package com.shobu.walk_in_clinic.ui.navigation.auth_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shobu.walk_in_clinic.ui.auth.launch.LaunchScreen
import com.shobu.walk_in_clinic.ui.auth.login.LoginScreen
import com.shobu.walk_in_clinic.ui.auth.signup.SignupScreen

@Composable
fun AuthNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AuthNavRoutes.Launch.route) {

        composable(AuthNavRoutes.Launch.route) {
            LaunchScreen(
                title = AuthNavRoutes.Launch.title,
                navController = navController
            )
        }

        composable(AuthNavRoutes.Login.route) {
            LoginScreen(
                title = AuthNavRoutes.Login.title,
                navController = navController
            )
        }

        composable(AuthNavRoutes.Signup.route) {
            SignupScreen(
                AuthNavRoutes.Signup.title,
                navController = navController,
            )
        }

    }
}