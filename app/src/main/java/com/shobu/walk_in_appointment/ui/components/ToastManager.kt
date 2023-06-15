package com.shobu.walk_in_appointment.ui.components

import android.content.Context
import android.widget.Toast


fun ShowToastAlert(
    context: Context,
    message: String,
) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}