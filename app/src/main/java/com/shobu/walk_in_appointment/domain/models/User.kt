package com.shobu.walk_in_appointment.domain.models

data class User(
    var id: Int?,
    var fullName: String,
    var dob: String,
    var gender: String,
    var email: String,
    var password: String
)
