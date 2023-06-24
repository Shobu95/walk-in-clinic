package com.shobu.walk_in_appointment.ui.navigation.nav_drawer

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.shobu.walk_in_appointment.R

data class NavDrawerItems(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val onClick: () -> Unit
)

fun getNavList(): List<NavDrawerItems> {
    return listOf(
        NavDrawerItems(
            R.string.title_my_appointments,
            R.drawable.ic_calendar,
            {}
        ),
        NavDrawerItems(
            R.string.title_search_for_clinics,
            R.drawable.ic_search,
            {}
        ),
        NavDrawerItems(
            R.string.title_profile,
            R.drawable.ic_profile,
            {}
        ),
        NavDrawerItems(
            R.string.title_logout,
            R.drawable.ic_logout,
            {}
        ),
    )

}



