package com.app.walk_in_clinic.ui.book_appointment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.app.walk_in_clinic.domain.constants.Constants
import com.app.walk_in_clinic.ui.book_appointment.components.BookAppointmentBody
import com.app.walk_in_clinic.ui.main.MainActivity
import com.example.walk_in_appointment.ui.theme.WalkInClinicTheme
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
                val clinicAddress = intent.getStringExtra(Constants.CLINIC_ADDRESS)
                BookAppointmentBody(clinicName!!, clinicAddress!!)
            }
        }
    }

    companion object {
        fun navigateToHome(context: Context) {
            val intent = Intent(
                context,
                MainActivity::class.java
            )
            context.startActivity(intent)
            (context as Activity).finishAffinity()
        }
    }
}

