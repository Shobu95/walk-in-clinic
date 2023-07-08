package com.shobu.walk_in_clinic.data.local.review

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review_table")
data class ReviewEntity(
    @PrimaryKey var id: Int? = null,
    var appointmentId: Int,
    var rating: Int,
    var review: String,
)
