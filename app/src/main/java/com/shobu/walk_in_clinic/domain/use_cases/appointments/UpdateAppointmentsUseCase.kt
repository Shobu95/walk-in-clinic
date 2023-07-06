package com.shobu.walk_in_clinic.domain.use_cases.appointments

import android.os.Build
import androidx.annotation.RequiresApi
import com.shobu.walk_in_clinic.data.repository.AppointmentRepository
import com.shobu.walk_in_clinic.domain.enums.AppointmentStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class UpdateAppointmentsUseCase
@Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private val currentDate = LocalDate.now()

    operator fun invoke() {
        CoroutineScope(Dispatchers.IO).launch {
            appointmentRepository.getAll().collectLatest { appointments ->

                if (appointments.isEmpty()) return@collectLatest

                appointments.forEach { appointment ->
                    var appointmentDate = LocalDate.parse(appointment.date, formatter)
                    if (currentDate.isAfter(appointmentDate)) {
                        appointmentRepository.updateAppointment(
                            appointment = appointment.copy(
                                status = AppointmentStatus.COMPLETED.name
                            )
                        )
                    }
                }
            }
        }

    }
}