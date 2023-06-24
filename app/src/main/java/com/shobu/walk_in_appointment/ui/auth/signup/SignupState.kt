package com.shobu.walk_in_appointment.ui.auth.signup

data class SignupState(
    var fullName: String = "",
    var phoneNumber: String = "",
    var dateOfBirth: String = "",
    var gender: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var signupSuccess: Boolean = false,
)

data class SignupFailedState(
    var isFailed: Boolean = false,
    var message: String = ""
)