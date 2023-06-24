package com.shobu.walk_in_appointment.ui.navigation.home_nav

import androidx.annotation.StringRes
import com.shobu.walk_in_appointment.R

sealed class MainNavRoutes(
    @StringRes var title: Int,
    var route: String,
) {

    object Home :
        MainNavRoutes(
            R.string.title_home,
            "home"
        )

    object MyAppointments :
        MainNavRoutes(
            R.string.title_my_appointments,
            "my-appointments"
        )

    object Profile :
        MainNavRoutes(
            R.string.title_profile,
            "profile"
        )

}