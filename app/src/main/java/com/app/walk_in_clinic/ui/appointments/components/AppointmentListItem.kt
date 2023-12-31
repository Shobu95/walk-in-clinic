package com.app.walk_in_clinic.ui.appointments.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.walk_in_clinic.R
import com.app.walk_in_clinic.domain.enums.AppointmentStatus
import com.app.walk_in_clinic.domain.models.Appointment
import com.app.walk_in_clinic.domain.utils.getDayFromDate
import com.app.walk_in_clinic.domain.utils.getMonthFromDate
import com.example.walk_in_appointment.ui.theme.BookedYellow
import com.example.walk_in_appointment.ui.theme.CompletedGreen
import com.example.walk_in_appointment.ui.theme.LightCyan

@Preview
@Composable
fun AppointmentListItemPrev() {
    AppointmentListItem(
        Appointment(
            id = null,
            locationName = "Agha Khan Hospital",
            date = "22/12/2023",
            slot = "8:30 AM",
            reason = "",
            status = AppointmentStatus.BOOKED.name
        )
    ) {}
}

@Composable
fun AppointmentListItem(
    appointment: Appointment,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(130.dp)
            .background(Color.White)
            .clickable { onClick() }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .padding(10.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2F)
            ) {

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(LightCyan)
                        .padding(10.dp)
                        .size(width = 65.dp, height = 55.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = getDayFromDate(appointment.date),
                        fontSize = 28.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(
                            Font(R.font.roboto),
                        )
                    )
                    Text(
                        text = getMonthFromDate(appointment.date),
                        fontSize = 12.sp,
                        color = Color.Black,
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = appointment.slot,
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                )

            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(4F)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    val clinicImage = painterResource(id = R.drawable.item_appointment_hospital)
                    Image(
                        painter = clinicImage,
                        contentDescription = "clinic image",
                        Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = appointment.locationName)
                }

                Row(
                    modifier = Modifier
                        .align(End)
                        .weight(1f, false)
                ) {
                    Text(
                        text = appointment.status,
                        style = TextStyle(
                            letterSpacing = 1.sp,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = if (appointment.status == "BOOKED") BookedYellow else CompletedGreen,
                    )
                }

            }
        }

    }
}