package com.shobu.walk_in_appointment.ui.profile

data class ProfileState(
    var fullName: String = "",
    var dateOfBirth: String = "",
    var gender: String = "",
    var totalAppointments: Int = 0,
    var height: String = "5'3",
    var weight: String = "60 KG"
)