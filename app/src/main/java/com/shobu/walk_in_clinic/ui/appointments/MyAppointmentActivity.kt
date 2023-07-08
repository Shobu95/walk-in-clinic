package com.shobu.walk_in_clinic.ui.appointments

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.walk_in_appointment.ui.theme.WalkInClinicTheme
import com.shobu.walk_in_clinic.R
import com.shobu.walk_in_clinic.domain.enums.AppointmentStatus
import com.shobu.walk_in_clinic.ui.appointments.components.AppointmentListItem
import com.shobu.walk_in_clinic.ui.components.MyTopAppBar
import com.shobu.walk_in_clinic.ui.components.RateAndReviewDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyAppointmentActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MyAppointmentScreenPrev() {
    MyAppointmentScreen()
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppointmentScreen(
    viewModel: MyAppointmentViewModel = hiltViewModel()
) {
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

            var showDialog by remember { mutableStateOf(false) }

            if (viewModel.state.myAppointments.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(
                        items = viewModel.state.myAppointments,
                        itemContent = { index, item ->
                            AppointmentListItem(appointment = item) {
                                if (item.status == AppointmentStatus.COMPLETED.name) {
                                    showDialog = true
                                }
                            }
                        }
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(20.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(id = R.string.no_appointment_message),
                        fontSize = 20.sp
                    )
                }
            }

            if (showDialog) {
                RateAndReviewDialog {
                    showDialog = false
                }
            }

        }
    }

}

