package com.app.walk_in_clinic.domain.use_cases.validations

import android.util.Patterns
import com.app.walk_in_clinic.ui.auth.signup.SignupState
import javax.inject.Inject

class ValidateUserUseCase
@Inject constructor(
    private val passwordValidation: PasswordValidationUseCase,
    private val phoneNumberValidationUseCase: PhoneNumberValidationUseCase
) {

    operator fun invoke(userState: SignupState): Pair<Boolean, String> {

        if (userState.fullName.isEmpty()) {
            return Pair(false, "Please enter full name")
        }

        if (!phoneNumberValidationUseCase(userState.phoneNumber).first) {
            return Pair(false, phoneNumberValidationUseCase(userState.phoneNumber).second)
        }

        if (userState.dateOfBirth.isEmpty()) {
            return Pair(false, "Please select date of birth")
        }

        if (userState.gender.isEmpty()) {
            return Pair(false, "Please select gender")
        }

        if (userState.height.isEmpty()) {
            return Pair(false, "Please enter height")
        }

        val pattern = Regex("""^\d+'(\d+)?$""")
        if (!pattern.containsMatchIn(userState.height)) {
            return Pair(false, "Please enter valid height")
        }

        if (userState.weight.isEmpty()) {
            return Pair(false, "Please enter weight")
        }

        if (userState.email.isEmpty()) {
            return Pair(false, "Please enter email")
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userState.email).matches()) {
            return Pair(false, "Please enter valid email")
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