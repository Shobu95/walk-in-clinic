package com.app.walk_in_clinic.ui.navigation.home_nav

import androidx.annotation.StringRes
import com.app.walk_in_clinic.R

sealed class MainNavRoutes(
    @StringRes var title: Int,
    var route: String,
) {

    object Home :
        MainNavRoutes(
            R.string.title_home,
            "home"
        )

}