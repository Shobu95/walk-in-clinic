package com.app.walk_in_clinic.domain.use_cases.validations

import javax.inject.Inject

class ValidateLoginFieldsUseCase
@Inject constructor() {

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