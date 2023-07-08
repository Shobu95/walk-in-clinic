package com.shobu.walk_in_clinic.domain.models

data class Review(
    var id: Int?,
    var appointmentId: Int,
    var rating: Int,
    var review: String,
)
