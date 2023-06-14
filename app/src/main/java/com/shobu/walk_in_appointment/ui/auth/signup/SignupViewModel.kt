package com.shobu.walk_in_appointment.ui.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shobu.walk_in_appointment.domain.models.User
import com.shobu.walk_in_appointment.domain.use_cases.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel
@Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
) : ViewModel() {

    var state by mutableStateOf(SignupState())
        private set

    fun onEvent(event: SignupEvents) {
        when (event) {

            is SignupEvents.OnStateChange -> {
                state = event.state
            }

            is SignupEvents.OnSignupClicked -> {
                CoroutineScope(Dispatchers.IO).launch {
                    createUserUseCase(
                        user = User(
                            id = null,
                            fullName = state.fullName,
                            dob = state.dob,
                            gender = state.gender,
                            email = state.email,
                            password = state.password
                        )
                    )
                    state = state.copy(
                        signupSuccess = true
                    )
                }

            }
        }
    }
}