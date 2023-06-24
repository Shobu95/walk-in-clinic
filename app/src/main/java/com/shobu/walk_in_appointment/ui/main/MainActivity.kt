package com.shobu.walk_in_appointment.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.walk_in_appointment.ui.theme.WalkInClinicTheme
import com.shobu.walk_in_appointment.ui.navigation.home_nav.MainNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalkInClinicTheme() {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }

    companion object {
        fun navigateToActivity(context: Context, activity: Activity) {
            val intent = Intent(
                context,
                activity::class.java
            )
            context.startActivity(intent)
            (context as Activity).finish()
        }
    }

}

@Preview
@Composable
fun HomeScreenPrev() {
    MainScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold() { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavGraph(navController = navController)
        }
    }
}
