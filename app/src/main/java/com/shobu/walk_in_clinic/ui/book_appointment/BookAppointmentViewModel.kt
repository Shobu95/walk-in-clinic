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
import java.time.LocalDateTime
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
            selectedSlot = "8:30 AM"
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
                    appointmentRepository.createAppointment(appointment = newAppointment)
                    alarmItem = AlarmItem(
                        time = LocalDateTime.now()
                            .plusSeconds("10".toLong()),
                        message = "${newAppointment.locationName} ${newAppointment.date} ${newAppointment.slot}"
                    )
                    alarmItem?.let(scheduler::schedule)


                    state = state.copy(
                        bookAppointmentSuccess = true
                    )
                }
            }

        }
    }


}