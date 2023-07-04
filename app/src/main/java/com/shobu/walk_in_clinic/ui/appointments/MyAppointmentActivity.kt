package com.shobu.walk_in_clinic.ui.appointments

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.walk_in_appointment.ui.theme.WalkInClinicTheme
import com.shobu.walk_in_clinic.R
import com.shobu.walk_in_clinic.ui.components.MyTopAppBar

class MyAppointmentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalkInClinicTheme {
                // A surface container using the 'background' color from the theme
                MyAppointmentScreen()
            }
        }
    }
}

@Preview
@Composable
fun MyAppointmentScreenPrev() {
    MyAppointmentScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppointmentScreen() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            MyTopAppBar(title = R.string.title_my_appointments, showBack = true) {
                (context as Activity).finish()
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.White)
        ) {


        }
    }

}

