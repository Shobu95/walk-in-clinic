package com.shobu.walk_in_clinic.ui.appointments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobu.walk_in_clinic.data.repository.AppointmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAppointmentViewModel
@Inject constructor(
    private val appointmentRepository: AppointmentRepository
) : ViewModel() {

    var state by mutableStateOf(MyAppointmentState())
        private set

    init {
        viewModelScope.launch {
            appointmentRepository.getAll().collectLatest { list ->
                state = state.copy(
                    myAppointments = list
                )
            }
        }
    }
}