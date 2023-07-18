package com.app.walk_in_clinic.domain.use_cases.appointments

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.walk_in_clinic.data.repository.AppointmentRepository
import com.app.walk_in_clinic.domain.enums.AppointmentStatus
import com.app.walk_in_clinic.domain.models.Appointment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetUpcomingAppointmentUseCase
@Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {

    private val _upcomingAppointmentState = MutableSharedFlow<Appointment>()
    val upcomingAppointmentState = _upcomingAppointmentState.asSharedFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke() {
        CoroutineScope(Dispatchers.IO).launch {
            appointmentRepository.getAll().collectLatest { list ->

                var allAppointments =
                    list.filter { it.status == AppointmentStatus.BOOKED.name }

                allAppointments = allAppointments.sortedBy {
                    LocalDate.parse(
                        it.date,
                        DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    )
                }

                if (allAppointments.isNotEmpty())
                    _upcomingAppointmentState.emit(allAppointments.first())
            }
        }
    }
}