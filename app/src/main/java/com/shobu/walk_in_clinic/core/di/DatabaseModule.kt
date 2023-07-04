package com.shobu.walk_in_clinic.core.di

import android.app.Application
import androidx.room.Room
import com.shobu.walk_in_clinic.core.database.ApplicationDB
import com.shobu.walk_in_clinic.data.local.appointment.AppointmentDao
import com.shobu.walk_in_clinic.data.local.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesApplicationDatabase(context: Application): ApplicationDB {
        return Room.databaseBuilder(
            context,
            ApplicationDB::class.java,
            ApplicationDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesUserDao(database: ApplicationDB): UserDao {
        return database.userDao
    }

    @Provides
    @Singleton
    fun providesAppointmentDao(database: ApplicationDB): AppointmentDao {
        return database.appointmentDao
    }
}