package com.shobu.walk_in_clinic.ui.book_appointment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookAppointmentViewModel
@Inject constructor() : ViewModel() {


    var state by mutableStateOf(BookAppointmentState())
        private set

    init {

    }

    fun onEvent(event: BookAppointmentEvents) {

        when (event) {

            is BookAppointmentEvents.OnStateChanged -> {
                state = event.state
            }

            BookAppointmentEvents.OnBookAppointmentClicked -> {

            }

        }
    }


}