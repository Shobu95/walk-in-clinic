package com.app.walk_in_clinic.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.walk_in_clinic.R

@Preview
@Composable
fun OkAlertDialogPrev() {
    OkAlertDialog(R.string.appointment_booking_successful) {}
}

@Composable
fun OkAlertDialog(
    @StringRes message: Int,
    onDismiss: () -> Unit
) {
    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = { },
        text = { Text(text = stringResource(id = message)) },
        confirmButton = {
            TextButton(onClick = { onDismiss() })
            { Text(text = "OK") }
        },
    )
}