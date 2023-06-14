package com.shobu.walk_in_appointment.ui.navigation.auth_nav

import androidx.annotation.StringRes
import com.shobu.walk_in_appointment.R

sealed class AuthNavRoutes(
    @StringRes var title: Int,
    var route: String,
) {

    object Launch :
        AuthNavRoutes(
            R.string.title_launch,
            "launch"
        )

    object Login :
        AuthNavRoutes(
            R.string.title_login,
            "login"
        )

    object Signup :
        AuthNavRoutes(
            R.string.title_register,
            "signup"
        )

}