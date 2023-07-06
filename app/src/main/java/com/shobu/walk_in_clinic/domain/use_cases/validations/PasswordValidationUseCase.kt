package com.shobu.walk_in_clinic.domain.use_cases.validations

import javax.inject.Inject

class PasswordValidationUseCase
@Inject constructor() {

    operator fun invoke(password: String): Pair<Boolean, String> {

        if (password.isEmpty()) {
            return Pair(false, "Please enter password")
        }

        if (password.length < 8) {
            return Pair(false, "Password cannot be less than 8 characters")
        }

        if (!password.hasUpperCase()) {
            return Pair(false, "Password should have at least 1 uppercase letter.")
        }

        if (!password.hasNumber()) {
            return Pair(false, "Password should have at least 1 number")
        }

        if (!password.hasSpecialChar()) {
            return Pair(false, "Password should have at least 1 special character")
        }

        return Pair(true, "")
    }

}


fun String.hasUpperCase(): Boolean {
    return this.any { char -> char.isUpperCase() }
}


fun String.hasNumber(): Boolean {
    return this.any { char -> char.isDigit() }
}

fun String.hasSpecialChar(): Boolean {
    return this.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
}