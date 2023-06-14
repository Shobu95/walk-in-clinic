package com.shobu.walk_in_appointment.ui.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.walk_in_appointment.ui.theme.AppTheme
import com.shobu.walk_in_appointment.ui.home.HomeActivity
import com.shobu.walk_in_appointment.ui.navigation.auth_nav.AuthNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme() {
                // A surface container using the 'background' color from the theme
                AuthScreen()
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
fun AuthScreenPrev() {
    AuthScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AuthScreen() {
    val navController = rememberNavController()
    Scaffold() { innerPadding ->
        Box(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
            AuthNavGraph(navController = navController)
        }
    }

}
