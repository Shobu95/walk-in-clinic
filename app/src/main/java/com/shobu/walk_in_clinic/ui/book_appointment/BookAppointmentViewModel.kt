package com.shobu.walk_in_clinic.ui.book_appointment

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shobu.walk_in_clinic.data.repository.AppointmentRepository
import com.shobu.walk_in_clinic.domain.enums.AppointmentStatus
import com.shobu.walk_in_clinic.domain.models.Appointment
import com.shobu.walk_in_clinic.domain.utils.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class BookAppointmentViewModel
@Inject constructor(
    private val appointmentRepository: AppointmentRepository
) : ViewModel() {


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
                    state = state.copy(
                        bookAppointmentSuccess = true
                    )
                }
            }

        }
    }


}