package com.shobu.walk_in_appointment.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey var id: Int? = null,
    var name: String,
    var dateOfBirth: String,
    var gender: String,
    var email: String,
    var password: String
)