package com.app.walk_in_clinic.ui.book_appointment

data class BookAppointmentState(
    var clinicLocation: String = "",
    var selectedDate: String = "",
    var selectedSlot: String = "",
    var reason: String = "",
    var bookAppointmentSuccess: Boolean = false
)

data class BookingFailedState(
    var isFailed: Boolean = false,
    var message: String = ""
)
