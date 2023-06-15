package com.shobu.walk_in_appointment.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shobu.walk_in_appointment.data.local.user.UserDao
import com.shobu.walk_in_appointment.data.local.user.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDB : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "application_database"
    }

    abstract val userDao: UserDao
}