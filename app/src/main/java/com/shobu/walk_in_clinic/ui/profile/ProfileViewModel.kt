package com.shobu.walk_in_clinic.ui.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobu.walk_in_clinic.domain.use_cases.appointments.GetMyAppointmentsUseCase
import com.shobu.walk_in_clinic.domain.use_cases.authentication.GetSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val getSessionUseCase: GetSessionUseCase,
    private val getMyAppointmentsUseCase: GetMyAppointmentsUseCase
) : ViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                fullName = getSessionUseCase().fullName,
                dateOfBirth = getSessionUseCase().dateOfBirth,
                gender = getSessionUseCase().gender,
                height = getSessionUseCase().height,
                weight = getSessionUseCase().weight
            )

            getMyAppointmentsUseCase.invoke()
            getMyAppointmentsUseCase.myAppointments.collectLatest {
                state = state.copy(
                    totalAppointments = it.size
                )
            }
        }
    }
}