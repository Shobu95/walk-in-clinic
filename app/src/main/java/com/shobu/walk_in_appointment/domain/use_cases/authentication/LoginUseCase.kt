package com.shobu.walk_in_appointment.domain.use_cases.authentication

import com.shobu.walk_in_appointment.domain.models.User
import com.shobu.walk_in_appointment.domain.use_cases.GetAllUsersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) {

    private val _loginState = MutableSharedFlow<LoginUseCaseResponse>()
    val loginState = _loginState.asSharedFlow()

    private var users = emptyList<User>()
    private lateinit var selectedUser: User

    operator fun invoke(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            getAllUsersUseCase().collectLatest {
                users = it
                if (!emailExists(users, email)) {
                    _loginState.emit(
                        LoginUseCaseResponse.OnLoginFailed(
                            message = "Email does not exist"
                        )
                    )
                } else {
                    if (!userLoggedIn(password)) {
                        _loginState.emit(
                            LoginUseCaseResponse.OnLoginFailed(
                                message = "Incorrect Password"
                            )
                        )
                    } else {
                        _loginState.emit(
                            LoginUseCaseResponse.OnLoginSuccess(selectedUser)
                        )
                    }
                }
            }
        }
    }

    private fun emailExists(users: List<User>, email: String): Boolean {
        var status: Boolean
        val filteredUsers = users.filter { it.email == email }

        if(filteredUsers.isNotEmpty()){
            selectedUser = filteredUsers[0]
            status = true
        } else {
            status = false
        }
        return status
    }

    private fun userLoggedIn(password: String): Boolean {
        return selectedUser.password == password
    }
}

sealed class LoginUseCaseResponse {
    data class OnLoginFailed(val message: String) : LoginUseCaseResponse()
    data class OnLoginSuccess(val user: User) : LoginUseCaseResponse()
}