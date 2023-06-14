package com.shobu.walk_in_appointment.ui.home.profile

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.shobu.walk_in_appointment.R


@Preview
@Composable
fun ProfileScreenPrev() {
    ProfileScreen(
        R.string.title_profile
    )
}

@Composable
fun ProfileScreen(
    @StringRes title: Int,
) {

}