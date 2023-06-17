package com.shobu.walk_in_appointment.ui.home.upcoming

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobu.walk_in_appointment.domain.use_cases.ClearUserSessionUseCase
import com.shobu.walk_in_appointment.domain.use_cases.GetSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel
@Inject constructor(
    private val getSessionUseCase: GetSessionUseCase,
    private val clearUserSession: ClearUserSessionUseCase
) : ViewModel() {

    var state by mutableStateOf(UpcomingState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                fullName = getSessionUseCase().fullName
            )
        }
    }

    fun onEvent(event: UpcomingEvent) {
        when (event) {
            UpcomingEvent.OnLogout -> {
                clearUserSession()
                state = state.copy(
                    onLogout = true
                )
            }
        }
    }

}