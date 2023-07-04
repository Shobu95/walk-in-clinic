package com.shobu.walk_in_clinic.domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentDate(): String {

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val currentDate = LocalDate.now()
    val newDate = LocalDate.of(
        currentDate.year,
        currentDate.monthValue,
        currentDate.dayOfMonth
    ).format(formatter)

    return newDate.toString()
}