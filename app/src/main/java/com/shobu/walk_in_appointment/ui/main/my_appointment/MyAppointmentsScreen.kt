package com.shobu.walk_in_appointment.ui.main.my_appointment

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.shobu.walk_in_appointment.R


@Preview
@Composable
fun MyAppointmentsScreenPrev() {
    MyAppointmentsScreen(R.string.title_my_appointments)
}

@Composable
fun MyAppointmentsScreen(
    @StringRes title: Int,
) {

}