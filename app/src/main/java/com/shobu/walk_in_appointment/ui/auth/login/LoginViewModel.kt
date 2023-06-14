package com.shobu.walk_in_appointment.ui.auth.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobu.walk_in_appointment.domain.use_cases.LoginUseCase
import com.shobu.walk_in_appointment.domain.use_cases.LoginUseCaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private var _loginFailState = MutableSharedFlow<LoginFailedState>()
    val loginFailState: SharedFlow<LoginFailedState> = _loginFailState.asSharedFlow()


    init {
        viewModelScope.launch {
            loginUseCase.loginState.collectLatest { event ->
                when (event) {
                    is LoginUseCaseResponse.OnLoginFailed -> {
                        Log.d("email_exists", "here")
                        _loginFailState.emit(
                            LoginFailedState(
                                isFailed = true,
                                message = event.message
                            )
                        )
                    }

                    LoginUseCaseResponse.OnLoginSuccess -> {
                        state = state.copy(
                            loginSuccess = true
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: LoginEvents) {
        when (event) {

            is LoginEvents.OnStateChange -> {
                state = event.state
            }

            LoginEvents.OnLoginClicked -> {
                loginUseCase.invoke(state.email, state.password)
            }
        }
    }
}