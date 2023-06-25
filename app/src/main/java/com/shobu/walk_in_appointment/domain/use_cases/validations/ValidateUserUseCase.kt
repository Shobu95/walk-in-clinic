package com.shobu.walk_in_appointment.domain.use_cases.validations

import android.util.Patterns
import com.shobu.walk_in_appointment.ui.auth.signup.SignupState
import javax.inject.Inject

class ValidateUserUseCase
@Inject constructor(
    private val passwordValidation: PasswordValidationUseCase
) {

    operator fun invoke(userState: SignupState): Pair<Boolean, String> {

        if (userState.fullName.isEmpty()) {
            return Pair(false, "Please enter full name")
        }

        if (userState.phoneNumber.isEmpty()) {
            return Pair(false, "Please enter phone number")
        }

        if (userState.dateOfBirth.isEmpty()) {
            return Pair(false, "Please select date of birth")
        }

        if (userState.gender.isEmpty()) {
            return Pair(false, "Please select gender")
        }

        if (userState.email.isEmpty()) {
            return Pair(false, "Please enter email")
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userState.email).matches()) {
            return Pair(false, "Please enter valid email")
        }

        if (userState.password.isEmpty()) {
            return Pair(false, "Please enter password")
        }

        if (!passwordValidation(userState.password).first) {
            return Pair(false, passwordValidation(userState.password).second)
        }

        if (userState.password != userState.confirmPassword) {
            return Pair(false, "Passwords do not match!")
        }

        return Pair(true, "")


    }
}