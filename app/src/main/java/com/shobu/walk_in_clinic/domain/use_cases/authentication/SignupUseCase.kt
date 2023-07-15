package com.shobu.walk_in_clinic.domain.use_cases.authentication

import com.shobu.walk_in_clinic.domain.models.User
import com.shobu.walk_in_clinic.domain.use_cases.GetAllUsersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignupUseCase
@Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) {

    private lateinit var selectedUser: User

    private val _signupState = MutableSharedFlow<SignupUseCaseResponse>()
    val signupState = _signupState.asSharedFlow()


    operator fun invoke(email: String) {
        var observerStatus = true
        CoroutineScope(Dispatchers.IO).launch {
            getAllUsersUseCase().collectLatest {
                if (emailExists(it, email)) {
                    if (observerStatus) {
                        _signupState.emit(
                            SignupUseCaseResponse.OnEmailExists
                        )
                        observerStatus = false
                    }
                } else {
                    if (observerStatus) {
                        _signupState.emit(
                            SignupUseCaseResponse.OnEmailNotExists
                        )
                        observerStatus = false
                    }
                }
            }
        }
    }

    private fun emailExists(users: List<User>, email: String): Boolean {
        var status: Boolean
        val filteredUsers = users.filter { it.email == email }

        if (filteredUsers.isNotEmpty()) {
            selectedUser = filteredUsers[0]
            status = true
        } else {
            status = false
        }
        return status
    }
}


sealed class SignupUseCaseResponse {
    object OnEmailExists : SignupUseCaseResponse()
    object OnEmailNotExists : SignupUseCaseResponse()
}