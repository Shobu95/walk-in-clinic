package com.app.walk_in_clinic.ui.book_appointment.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.walk_in_clinic.R

@Preview
@Composable
fun ClinicHeadingPrev() {
    ClinicHeading("Aga Khan Hospital")
}

@Composable
fun ClinicHeading(clinicLocation: String) {
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
            text = clinicLocation,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.roboto))
        )
    }
}