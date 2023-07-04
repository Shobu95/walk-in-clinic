package com.shobu.walk_in_clinic.ui.book_appointment

sealed interface BookAppointmentEvents {

    data class OnStateChanged(val state: BookAppointmentState) : BookAppointmentEvents

    object OnBookAppointmentClicked : BookAppointmentEvents
}