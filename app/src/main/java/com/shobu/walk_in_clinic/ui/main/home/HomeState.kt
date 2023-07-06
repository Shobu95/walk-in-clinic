package com.shobu.walk_in_clinic.ui.main.home

import com.shobu.walk_in_clinic.domain.models.Appointment

data class HomeState(
    var fullName: String = "",
    var appointment: Appointment = Appointment(
        id = null,
        locationName = "",
        date = "",
        slot = "",
        reason = "",
        status = ""
    ),
    var onLogout: Boolean = false,
)