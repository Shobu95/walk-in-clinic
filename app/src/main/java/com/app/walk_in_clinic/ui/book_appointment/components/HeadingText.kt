package com.app.walk_in_clinic.ui.book_appointment.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.walk_in_clinic.R

@Preview
@Composable
private fun HeadingTextPrev() {
    HeadingText("Selected Clinic")
}

@Composable
fun HeadingText(heading: String) {
    Text(
        text = heading,
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(12.dp))
}