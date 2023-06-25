package com.shobu.walk_in_clinic.ui.profile

data class ProfileState(
    var fullName: String = "",
    var dateOfBirth: String = "",
    var gender: String = "",
    var totalAppointments: Int = 0,
    var height: String = "5'7",
    var weight: String = "160 LBS"
)