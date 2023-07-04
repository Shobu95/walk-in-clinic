package com.shobu.walk_in_clinic.ui.book_appointment

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.walk_in_appointment.ui.theme.WalkInClinicTheme
import com.shobu.walk_in_clinic.domain.constants.Constants
import com.shobu.walk_in_clinic.ui.book_appointment.components.BookAppointmentBody
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookAppointmentActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalkInClinicTheme {
                // A surface container using the 'background' color from the theme
                val clinicName = intent.getStringExtra(Constants.CLINIC_NAME)
                BookAppointmentBody(clinicName!!)
            }
        }
    }
}

