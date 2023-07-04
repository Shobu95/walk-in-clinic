package com.shobu.walk_in_clinic.domain.models

data class Appointment(
    var id: Int?,
    var locationName: String,
    var date: String,
    var slot: String,
    var reason: String,
    var status: String
)
