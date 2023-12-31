package com.app.walk_in_clinic.ui.appointments

import com.app.walk_in_clinic.domain.models.Appointment
import com.app.walk_in_clinic.domain.models.Review

data class MyAppointmentState(
    val myAppointments: List<Appointment> = emptyList(),
)

data class OpenReviewDialogState(
    val openDialog: Boolean = false,
    val review: Review? = null,
    val appointmentId: Int? = null
)
