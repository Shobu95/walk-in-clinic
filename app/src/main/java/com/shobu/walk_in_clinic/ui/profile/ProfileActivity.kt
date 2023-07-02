package com.shobu.walk_in_clinic.ui.profile

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.walk_in_appointment.ui.theme.WalkInClinicTheme
import com.shobu.walk_in_clinic.R
import com.shobu.walk_in_clinic.ui.components.MyTopAppBar
import com.shobu.walk_in_clinic.ui.profile.components.PersonalDetails
import com.shobu.walk_in_clinic.ui.profile.components.ProfileDetailsItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalkInClinicTheme {
                // A surface container using the 'background' color from the theme
                ProfileScreen()
            }
        }
    }

}

@Preview
@Composable
fun ProfileScreenPrev() {
    ProfileScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            MyTopAppBar(title = R.string.title_profile, showBack = true) {
                (context as Activity).finish()
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.White)
        ) {


            Spacer(modifier = Modifier.height(10.dp))

            PersonalDetails(viewModel.state.fullName)

            Spacer(modifier = Modifier.height(20.dp))

            ProfileDetailsItem(
                R.drawable.ic_calendar,
                viewModel.state.dateOfBirth,
                "Date of Birth"
            )

            ProfileDetailsItem(
                R.drawable.ic_gender,
                viewModel.state.gender,
                "Gender"
            )

            ProfileDetailsItem(
                R.drawable.ic_appointment_book,
                viewModel.state.totalAppointments.toString(),
                "Total Appointments"
            )

            ProfileDetailsItem(
                R.drawable.ic_height,
                viewModel.state.height,
                "Height"
            )

            ProfileDetailsItem(
                R.drawable.ic_weight,
                viewModel.state.weight + " lbs",
                "Weight"
            )

        }
    }


}
