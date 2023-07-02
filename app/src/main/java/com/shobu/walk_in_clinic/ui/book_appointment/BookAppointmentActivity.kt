package com.shobu.walk_in_clinic.ui.book_appointment

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.walk_in_appointment.ui.theme.WalkInClinicTheme
import com.foreverrafs.datepicker.DatePickerTimeline
import com.foreverrafs.datepicker.state.rememberDatePickerState
import com.shobu.walk_in_clinic.R
import com.shobu.walk_in_clinic.domain.constants.Constants
import com.shobu.walk_in_clinic.ui.book_appointment.components.HeadingText
import com.shobu.walk_in_clinic.ui.components.BorderedTextArea
import com.shobu.walk_in_clinic.ui.components.MyTopAppBar
import com.shobu.walk_in_clinic.ui.components.PrimaryButton
import java.time.LocalDate

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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun BookAppointmentBodyPrev() {
    BookAppointmentBody("clinicName")
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun BookAppointmentBody(clinicName: String) {
    val context = LocalContext.current
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

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hospital),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(50.dp),
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = clinicName,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            HeadingText(heading = "Select Date")

            val datePickerState = rememberDatePickerState(initialDate = LocalDate.now())
            DatePickerTimeline(
                state = datePickerState,
                selectedBackgroundColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = Color.White,
                dateTextColor = Color.Black,
                eventIndicatorColor = Color.Blue,
                pastDaysCount = 0,
                onDateSelected = {}
            )
            Spacer(modifier = Modifier.height(16.dp))


            HeadingText(heading = "Select Slot")


            val list = listOf(
                "8:30 AM",
                "9:30 AM",
                "10:30 AM",
                "11:30 AM",
                "12:30 PM",
                "2:00 PM",
                "3:00 PM"
            )

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(items = list, itemContent = { item ->
                    Card(
                        shape = RoundedCornerShape(22.dp),
                        modifier = Modifier
                            .background(Color.White)
                            .padding(5.dp)
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier
                                .background(Color.LightGray)
                                .padding(vertical = 8.dp, horizontal = 10.dp)
                        )
                    }
                })
            }
            Spacer(modifier = Modifier.height(16.dp))


            HeadingText(heading = "Add Reason")
            Spacer(modifier = Modifier.height(12.dp))

            var reasonValue = remember { mutableStateOf("") }
            BorderedTextArea(
                hint = R.string.reason_hint,
                textFieldValue = reasonValue,
            ) {

            }

            Spacer(modifier = Modifier.height(30.dp))

            PrimaryButton(
                buttonText = R.string.title_book_appointment,
                backgroundColor = MaterialTheme.colorScheme.primary
            ) {

            }


        }
    }


}
