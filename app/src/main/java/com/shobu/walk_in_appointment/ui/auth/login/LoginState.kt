package com.shobu.walk_in_appointment.ui.auth.login

import com.shobu.walk_in_appointment.domain.use_cases.LoginUseCaseResponse

data class LoginState(
    var email: String = "",
    var password: String = "",
    var loginSuccess: Boolean = false,
)


data class LoginFailedState(
    var isFailed: Boolean = false,
    var message: String = ""
)