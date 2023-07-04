package com.shobu.walk_in_clinic.ui.appointments

import com.shobu.walk_in_clinic.domain.models.Appointment

data class MyAppointmentState(
    val myAppointments: List<Appointment> = emptyList()
)
