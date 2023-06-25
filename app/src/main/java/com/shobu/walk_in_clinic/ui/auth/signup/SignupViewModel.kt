package com.shobu.walk_in_clinic.ui.auth.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shobu.walk_in_clinic.domain.models.User
import com.shobu.walk_in_clinic.domain.use_cases.authentication.CreateUserUseCase
import com.shobu.walk_in_clinic.domain.use_cases.authentication.SaveUserSessionUseCase
import com.shobu.walk_in_clinic.domain.use_cases.validations.ValidateUserUseCase
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
    private val saveUserSession: SaveUserSessionUseCase,
    private val validateUserUseCase: ValidateUserUseCase
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
                        phoneNumber = state.phoneNumber,
                        dateOfBirth = state.dateOfBirth,
                        gender = state.gender,
                        email = state.email,
                        password = state.password
                    )

                    if (validateUserUseCase(state).first) {
                        createUserUseCase(user = newUser)
                        state = state.copy(
                            signupSuccess = true
                        )
                        saveUserSession(newUser)
                    } else
                        _signupFailState.emit(
                            SignupFailedState(
                                isFailed = true,
                                message = validateUserUseCase(state).second
                            )
                        )
                }

            }
        }
    }
}