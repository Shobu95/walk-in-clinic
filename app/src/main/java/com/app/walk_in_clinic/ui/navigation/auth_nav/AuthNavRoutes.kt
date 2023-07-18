package com.app.walk_in_clinic.ui.navigation.auth_nav

import androidx.annotation.StringRes
import com.app.walk_in_clinic.R

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
            R.string.create_an_account,
            "signup"
        )

}