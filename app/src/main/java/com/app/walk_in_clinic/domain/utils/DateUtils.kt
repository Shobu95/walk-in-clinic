package com.app.walk_in_clinic.domain.utils

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

fun getDayFromDate(date: String): String {
    return date.split('/')[0]
}

fun getMonthFromDate(date: String): String {
    return when (date.split('/')[1]) {
        "01" -> "January"
        "02" -> "February"
        "03" -> "March"
        "04" -> "April"
        "05" -> "May"
        "06" -> "June"
        "07" -> "July"
        "08" -> "August"
        "09" -> "September"
        "10" -> "October"
        "11" -> "November"
        "12" -> "December"
        else -> "NaN"
    }
}

