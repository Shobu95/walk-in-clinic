package com.shobu.walk_in_appointment.domain.use_cases

import javax.inject.Inject

class ValidateLoginFieldsUseCase
@Inject constructor(){

    operator fun invoke(email: String, password: String): Pair<Boolean, String> {

        if (email.isNullOrEmpty()) {
            return Pair(false, "Please enter valid email")
        }

        if (password.isNullOrEmpty()) {
            return Pair(false, "Please enter password")
        }

        return Pair(true, "")
    }

}