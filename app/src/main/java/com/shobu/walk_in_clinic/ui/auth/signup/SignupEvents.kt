package com.shobu.walk_in_clinic.ui.auth.signup

sealed class SignupEvents {
    data class OnStateChange(val state: SignupState) : SignupEvents()
    object OnSignupClicked: SignupEvents()
}