package com.app.walk_in_clinic.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.walk_in_clinic.data.local.appointment.AppointmentDao
import com.app.walk_in_clinic.data.local.appointment.AppointmentEntity
import com.app.walk_in_clinic.data.local.review.ReviewDao
import com.app.walk_in_clinic.data.local.review.ReviewEntity
import com.app.walk_in_clinic.data.local.user.UserDao
import com.app.walk_in_clinic.data.local.user.UserEntity

@Database(
    entities = [UserEntity::class, AppointmentEntity::class, ReviewEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDB : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "application_database"
    }

    abstract val userDao: UserDao
    abstract val appointmentDao: AppointmentDao
    abstract val reviewDao: ReviewDao
}