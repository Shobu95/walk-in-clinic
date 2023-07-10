package com.shobu.walk_in_clinic.ui.book_appointment

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shobu.walk_in_clinic.core.alarm_manager.AlarmItem
import com.shobu.walk_in_clinic.core.alarm_manager.AndroidAlarmScheduler
import com.shobu.walk_in_clinic.data.repository.AppointmentRepository
import com.shobu.walk_in_clinic.domain.enums.AppointmentStatus
import com.shobu.walk_in_clinic.domain.models.Appointment
import com.shobu.walk_in_clinic.domain.utils.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class BookAppointmentViewModel
@Inject constructor(
    @ApplicationContext private val context: Context,
    private val appointmentRepository: AppointmentRepository
) : ViewModel() {

    val scheduler = AndroidAlarmScheduler(context)
    var alarmItem: AlarmItem? = null

    var state by mutableStateOf(BookAppointmentState())
        private set

    init {
        state = state.copy(
            selectedDate = getCurrentDate(),
            selectedSlot = "08:30 am"
        )
    }

    fun onEvent(event: BookAppointmentEvents) {

        when (event) {

            is BookAppointmentEvents.OnStateChanged -> {
                state = event.state
                Log.v("book_appointment", state.toString())
            }

            BookAppointmentEvents.OnBookAppointmentClicked -> {

                val newAppointment = Appointment(
                    id = null,
                    locationName = state.clinicLocation,
                    date = state.selectedDate,
                    slot = state.selectedSlot,
                    reason = state.reason,
                    status = AppointmentStatus.BOOKED.name
                )

                CoroutineScope(Dispatchers.IO).launch {

                    val date = newAppointment.date
                    val time = newAppointment.slot

                    // Create a DateTimeFormatter for the date and time.
                    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.UK)

                    // Parse the date and time strings into LocalDateTime objects.
                    val localDate = LocalDate.parse(date, dateFormatter)
                    val localTime = LocalTime.parse(time, timeFormatter)

                    // Combine the date and time objects into a single LocalDateTime object.
                    val localDateTime = localDate.atTime(localTime)

                    // Print the LocalDateTime object.
                    println("Local Date Time: $localDateTime")

                    alarmItem = AlarmItem(
                        time = localDateTime.minusMinutes("15".toLong()),
                        message = getMessage(
                            newAppointment.locationName,
                            newAppointment.date,
                            newAppointment.slot
                        )
                    )
                    alarmItem?.let(scheduler::schedule)

                    appointmentRepository.createAppointment(appointment = newAppointment)

                    state = state.copy(
                        bookAppointmentSuccess = true
                    )
                }
            }

        }
    }

    private fun getMessage(location: String, date: String, time: String): String {
        return "You have an appointment today at $location at $time. Please be on time."
    }


}