package com.shobu.walk_in_clinic.ui.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shobu.walk_in_clinic.domain.use_cases.authentication.LoginUseCase
import com.shobu.walk_in_clinic.domain.use_cases.authentication.LoginUseCaseResponse
import com.shobu.walk_in_clinic.domain.use_cases.authentication.SaveUserSessionUseCase
import com.shobu.walk_in_clinic.domain.use_cases.validations.ValidateLoginFieldsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveUserSession: SaveUserSessionUseCase,
    private val loginValidation: ValidateLoginFieldsUseCase = ValidateLoginFieldsUseCase()
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
                        _loginFailState.emit(
                            LoginFailedState(
                                isFailed = true,
                                message = event.message
                            )
                        )
                    }

                    is LoginUseCaseResponse.OnLoginSuccess -> {
                        saveUserSession(event.user)
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
                if (loginValidation(state.email, state.password).first)
                    loginUseCase.invoke(state.email, state.password)
                else
                    viewModelScope.launch {
                        _loginFailState.emit(
                            LoginFailedState(
                                isFailed = true,
                                message = loginValidation(state.email, state.password).second
                            )
                        )
                    }
            }

        }
    }
}