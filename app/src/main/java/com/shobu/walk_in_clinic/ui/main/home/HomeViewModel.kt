package com.shobu.walk_in_clinic.ui.main.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobu.walk_in_clinic.domain.use_cases.appointments.GetUpcomingAppointmentUseCase
import com.shobu.walk_in_clinic.domain.use_cases.authentication.ClearUserSessionUseCase
import com.shobu.walk_in_clinic.domain.use_cases.authentication.GetSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getSessionUseCase: GetSessionUseCase,
    private val clearUserSession: ClearUserSessionUseCase,
    private val getUpcomingAppointmentUseCase: GetUpcomingAppointmentUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                fullName = getSessionUseCase().fullName
            )
            getUpcomingAppointmentUseCase.invoke()
            getUpcomingAppointmentUseCase.upcomingAppointmentState.collectLatest { appointment ->
                state = state.copy(
                    appointment = appointment
                )
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnLogout -> {
                clearUserSession()
                state = state.copy(
                    onLogout = true
                )
            }
        }
    }

}