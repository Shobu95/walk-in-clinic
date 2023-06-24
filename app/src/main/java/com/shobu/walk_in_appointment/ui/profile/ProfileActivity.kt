package com.shobu.walk_in_appointment.ui.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.walk_in_appointment.ui.theme.WalkInClinicTheme
import com.shobu.walk_in_appointment.R
import com.shobu.walk_in_appointment.ui.profile.components.PersonalDetails
import com.shobu.walk_in_appointment.ui.profile.components.ProfileDetailsItem
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

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        PersonalDetails(viewModel.state.fullName)

        Spacer(modifier = Modifier.height(40.dp))

        ProfileDetailsItem(
            R.drawable.ic_calendar,
            viewModel.state.dateOfBirth,
            "Date of Birth"
        )

    }
}
