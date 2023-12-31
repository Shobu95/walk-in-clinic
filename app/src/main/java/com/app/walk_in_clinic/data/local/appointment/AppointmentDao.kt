package com.app.walk_in_clinic.data.local.appointment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {

    @Insert
    fun createAppointment(appointment: AppointmentEntity)

    @Query("SELECT * FROM appointment_table")
    fun getAllAppointments(): Flow<List<AppointmentEntity>>

    @Update
    fun updateAppointment(appointment: AppointmentEntity)
}