package com.shobu.walk_in_clinic.ui.auth.login


sealed class LoginEvents {
    data class OnStateChange(val state: LoginState) : LoginEvents()
    object OnLoginClicked: LoginEvents()
}