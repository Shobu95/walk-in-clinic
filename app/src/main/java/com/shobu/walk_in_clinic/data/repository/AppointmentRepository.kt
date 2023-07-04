package com.shobu.walk_in_clinic.data.repository

import com.shobu.walk_in_clinic.data.local.appointment.AppointmentDao
import com.shobu.walk_in_clinic.data.local.appointment.AppointmentEntity
import com.shobu.walk_in_clinic.domain.models.Appointment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppointmentRepository
@Inject constructor(
    private val appointmentDao: AppointmentDao
) {
    fun getAll(): Flow<List<Appointment>> {
        return appointmentDao.getAllAppointments().map { it ->
            it.asDomainModelList()
        }
    }

    fun createAppointment(appointment: Appointment) {
        appointmentDao.createAppointment(appointment.asEntityModel())
    }

}

fun Appointment.asEntityModel(): AppointmentEntity {
    return AppointmentEntity(
        id = this.id,
        locationName = this.locationName,
        date = this.date,
        slot = this.slot,
        reason = this.reason,
        status = this.status,
    )
}

fun List<AppointmentEntity>.asDomainModelList(): List<Appointment> {
    return map {
        Appointment(
            id = it.id,
            locationName = it.locationName,
            date = it.date,
            slot = it.slot,
            reason = it.reason,
            status = it.status,
        )
    }
}