package com.app.walk_in_clinic.ui.appointments.components

sealed interface MyAppointmentEvents {
    data class OpenReviewDialog(
        val appointmentId: Int
    ) : MyAppointmentEvents

    object ClosReviewDialog : MyAppointmentEvents

    data class SaveReview(
        val rating: Int,
        val review: String,
    ) : MyAppointmentEvents
}