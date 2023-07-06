package com.shobu.walk_in_clinic.ui.appointments

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobu.walk_in_clinic.domain.use_cases.appointments.GetMyAppointmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MyAppointmentViewModel
@Inject constructor(
    private val getMyAppointmentsUseCase: GetMyAppointmentsUseCase
) : ViewModel() {

    var state by mutableStateOf(MyAppointmentState())
        private set

    init {
        viewModelScope.launch {
            getMyAppointmentsUseCase.invoke()
            getMyAppointmentsUseCase.myAppointments.collectLatest { list ->
                state = state.copy(
                    myAppointments = list
                )
            }
        }
    }
}