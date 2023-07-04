package com.shobu.walk_in_clinic.ui.book_appointment

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shobu.walk_in_clinic.domain.utils.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class BookAppointmentViewModel
@Inject constructor(

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

            }

        }
    }


}