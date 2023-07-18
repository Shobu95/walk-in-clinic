package com.app.walk_in_clinic.data.local.appointment

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointment_table")
data class AppointmentEntity(
    @PrimaryKey var id: Int? = null,
    var locationName: String,
    var date: String,
    var slot: String,
    var reason: String,
    var status: String,
)