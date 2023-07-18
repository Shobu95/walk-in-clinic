package com.app.walk_in_clinic.ui.profile

data class ProfileState(
    var fullName: String = "",
    var dateOfBirth: String = "",
    var gender: String = "",
    var totalAppointments: Int = 0,
    var height: String = "",
    var weight: String = ""
)