package com.shobu.walk_in_appointment.ui.auth.login


sealed class LoginEvents {
    data class OnStateChange(val state: LoginState) : LoginEvents()
    object OnLoginClicked: LoginEvents()
}