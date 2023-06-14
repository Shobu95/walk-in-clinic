package com.shobu.walk_in_appointment.ui.auth.signup

data class SignupState(
    var fullName: String = "",
    var dob: String = "",
    var gender: String = "",
    var email: String = "",
    var password: String = "",
    var signupSuccess: Boolean = false,
)