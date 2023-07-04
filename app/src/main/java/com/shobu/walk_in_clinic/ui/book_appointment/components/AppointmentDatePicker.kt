package com.shobu.walk_in_clinic.ui.book_appointment.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.foreverrafs.datepicker.DatePickerTimeline
import com.foreverrafs.datepicker.state.rememberDatePickerState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AppointmentDatePickerPrev() {
    AppointmentDatePicker() {}
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppointmentDatePicker(
    onDateSelected: (String) -> Unit
) {
    val datePickerState = rememberDatePickerState(initialDate = LocalDate.now())
    DatePickerTimeline(
        state = datePickerState,
        selectedBackgroundColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = Color.White,
        dateTextColor = Color.Black,
        eventIndicatorColor = Color.Blue,
        pastDaysCount = 0,
        onDateSelected = { localDate ->
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val newDate = LocalDate.of(
                localDate.year,
                localDate.monthValue,
                localDate.dayOfMonth
            ).format(formatter)
            onDateSelected(newDate.toString())
        }
    )
}
