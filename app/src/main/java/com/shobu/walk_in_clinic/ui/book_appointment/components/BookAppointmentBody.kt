package com.shobu.walk_in_clinic.ui.book_appointment.components

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shobu.walk_in_clinic.R
import com.shobu.walk_in_clinic.ui.book_appointment.BookAppointmentActivity
import com.shobu.walk_in_clinic.ui.book_appointment.BookAppointmentEvents
import com.shobu.walk_in_clinic.ui.book_appointment.BookAppointmentViewModel
import com.shobu.walk_in_clinic.ui.components.BorderedTextArea
import com.shobu.walk_in_clinic.ui.components.MyTopAppBar
import com.shobu.walk_in_clinic.ui.components.OkAlertDialog
import com.shobu.walk_in_clinic.ui.components.PrimaryButton
import com.shobu.walk_in_clinic.ui.components.ProgressDialog
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun BookAppointmentBodyPrev() {
    BookAppointmentBody("clinicName")
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookAppointmentBody(
    clinicLocation: String,
    viewModel: BookAppointmentViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            MyTopAppBar(title = R.string.title_book_appointment, showBack = true) {
                (context as Activity).finish()
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 18.dp, vertical = 14.dp)
                .background(Color.White)
        ) {

            HeadingText(heading = "Selected Clinic")
            ClinicHeading(clinicLocation = clinicLocation)
            viewModel.onEvent(
                BookAppointmentEvents.OnStateChanged(
                    state = viewModel.state.copy(
                        clinicLocation = clinicLocation,
                    )
                )
            )



            Spacer(modifier = Modifier.height(16.dp))
            HeadingText(heading = "Select Date")
            AppointmentDatePicker { selectedDate ->
                viewModel.onEvent(
                    BookAppointmentEvents.OnStateChanged(
                        state = viewModel.state.copy(
                            selectedDate = selectedDate
                        )
                    )
                )
            }



            Spacer(modifier = Modifier.height(16.dp))
            HeadingText(heading = "Select Slot")
            TimeSlotSelector { selectedSlot ->
                viewModel.onEvent(
                    BookAppointmentEvents.OnStateChanged(
                        state = viewModel.state.copy(
                            selectedSlot = selectedSlot
                        )
                    )
                )
            }



            Spacer(modifier = Modifier.height(16.dp))
            HeadingText(heading = "Add Reason")
            var reasonValue = remember { mutableStateOf("") }
            BorderedTextArea(
                hint = R.string.reason_hint,
                textFieldValue = reasonValue,
            ) { reason ->
                viewModel.onEvent(
                    BookAppointmentEvents.OnStateChanged(
                        state = viewModel.state.copy(
                            reason = reason
                        )
                    )
                )
            }


            Spacer(modifier = Modifier.height(30.dp))
            PrimaryButton(
                buttonText = R.string.title_book_appointment,
                backgroundColor = MaterialTheme.colorScheme.primary
            ) {
                viewModel.onEvent(BookAppointmentEvents.OnBookAppointmentClicked)
                showDialog = true
            }

            if (viewModel.state.bookAppointmentSuccess) {
                LaunchedEffect(key1 = null) {
                    delay(2500)
                    showDialog = false
                    showAlert = true


                }
            }

            if (showDialog) {
                ProgressDialog()
            }

            if (showAlert) {
                OkAlertDialog(message = R.string.appointment_booking_successful) {
                    BookAppointmentActivity.navigateToHome(context)
                }
            }

        }
    }

}