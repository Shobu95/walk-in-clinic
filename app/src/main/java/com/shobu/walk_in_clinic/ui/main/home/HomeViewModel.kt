package com.shobu.walk_in_clinic.ui.main.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobu.walk_in_clinic.domain.use_cases.authentication.ClearUserSessionUseCase
import com.shobu.walk_in_clinic.domain.use_cases.authentication.GetSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getSessionUseCase: GetSessionUseCase,
    private val clearUserSession: ClearUserSessionUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                fullName = getSessionUseCase().fullName
            )
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