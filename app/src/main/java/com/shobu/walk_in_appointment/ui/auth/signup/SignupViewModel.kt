package com.shobu.walk_in_appointment.ui.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shobu.walk_in_appointment.domain.models.User
import com.shobu.walk_in_appointment.domain.use_cases.CreateUserUseCase
import com.shobu.walk_in_appointment.domain.use_cases.ValidateUserUseCase
import com.shobu.walk_in_appointment.ui.auth.login.LoginFailedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel
@Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val validateUserUseCase: ValidateUserUseCase = ValidateUserUseCase()
) : ViewModel() {

    var state by mutableStateOf(SignupState())
        private set

    private var _signupFailState = MutableSharedFlow<SignupFailedState>()
    val signupFailState: SharedFlow<SignupFailedState> = _signupFailState.asSharedFlow()


    fun onEvent(event: SignupEvents) {
        when (event) {

            is SignupEvents.OnStateChange -> {
                state = event.state
            }

            is SignupEvents.OnSignupClicked -> {
                CoroutineScope(Dispatchers.IO).launch {

                    val newUser = User(
                        id = null,
                        fullName = state.fullName,
                        dob = state.dob,
                        gender = state.gender,
                        email = state.email,
                        password = state.password
                    )

                    if (validateUserUseCase(newUser).first) {
                        createUserUseCase(user = newUser)
                        state = state.copy(
                            signupSuccess = true
                        )
                    } else
                        _signupFailState.emit(
                            SignupFailedState(
                                isFailed = true,
                                message = validateUserUseCase(newUser).second
                            )
                        )
                }

            }
        }
    }
}