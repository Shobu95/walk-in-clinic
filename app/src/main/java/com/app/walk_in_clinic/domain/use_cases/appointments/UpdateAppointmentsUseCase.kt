package com.app.walk_in_clinic.domain.use_cases.appointments

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.walk_in_clinic.data.repository.AppointmentRepository
import com.app.walk_in_clinic.domain.enums.AppointmentStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class UpdateAppointmentsUseCase
@Inject constructor(
    private val appointmentRepository: AppointmentRepository
) {
    private val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private val currentDate = getCurrentDate()

    private val timeFormat = SimpleDateFormat("hh:mm aa")
    private val currentTime = getCurrentTime()


    operator fun invoke() {
        CoroutineScope(Dispatchers.IO).launch {
            appointmentRepository.getAll().collectLatest { appointments ->

                if (appointments.isEmpty()) return@collectLatest

                appointments.forEach { appointment ->
                    var appointmentDate = LocalDate.parse(appointment.date, dateFormat)
                    var appointmentTime = timeFormat.parse(appointment.slot)
                    if (currentDate.isEqual(appointmentDate) && currentTime.after(appointmentTime)) {
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

    private fun getCurrentTime(): Date {
        val mToday = Date()
        val currentTimeString = timeFormat.format(mToday)
        return timeFormat.parse(currentTimeString)
    }

    private fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }
}